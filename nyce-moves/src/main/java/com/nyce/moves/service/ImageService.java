package com.nyce.moves.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.common.UtilityFunctions;
import com.nyce.moves.model.CreateImageResponse;
import com.nyce.moves.model.CreateImageResponse.StatusEnum;
import com.nyce.moves.model.GetImagesResponse;
import com.nyce.moves.model.Image;
import com.nyce.moves.model.ImageRequest;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.repository.ImageRepository;
import com.nyce.moves.repository.PlayerRepository;

@Service
public class ImageService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	private AmazonClient amazonClient;

	public CreateImageResponse addImage(Long playerId, ImageRequest imageRequest,CreateImageResponse createImageResponse) {

		Image image = new Image();
		image.setApplauds(0l);
		image.setDescription(imageRequest.getDescription());
		image.setImageUrl(imageRequest.getImageUrl());
		image.setPlayerId(playerId);
		image.setPostedTimestamp(OffsetDateTime.now());
		image.setTitle(imageRequest.getTitle());
		long imageId = 0L;

		Player player = playerRepository.findOne(playerId);
		Player returnPlayer = null;

		if (player != null) {
			player.getImages().add(image);
			returnPlayer = playerRepository.save(player);
			if (returnPlayer != null && returnPlayer.getImages() != null && returnPlayer.getImages().size() > 0) {
				imageId = returnPlayer.getImages().get(returnPlayer.getImages().size() - 1).getImageId();
				image.setImageId(imageId);
			}
			createImageResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			createImageResponse.setMessage("Image has been successfully submitted for player [" + playerId + "]");
			if (image.getImageUrl() != null && image.getImageUrl() != "") {
				String objectName = amazonClient.getObjectNameFromS3Url(image.getImageUrl());
				String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
				image.setPreSignedImageUrl(presignedUrl);
			}
			createImageResponse.setData(image);
			createImageResponse.setStatus(CreateImageResponse.StatusEnum.SUCCESS);
		} else {
			createImageResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createImageResponse.setMessage("Image has not been successfully submitted for player [" + playerId + "], Because playerId doesn't exist");
			createImageResponse.setStatus(CreateImageResponse.StatusEnum.FAILURE);
		}

		return createImageResponse;
	}

	public Player deleteImage(Long playerId, Long imageId, ResponseTemplate responseTemplate) {
		
		Player player = playerRepository.findOne(playerId);
		Player returnPlayer = null;
		if(player != null){
			List<Image> images = player.getImages();
			
			boolean deleteIndicator = images.removeIf(p -> p.getImageId() == imageId);
			if(deleteIndicator){
				player.setImages(images);
				returnPlayer = playerRepository.save(player);
				imageRepository.delete(imageId);
				responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				responseTemplate.setMessage("Image has been deleted successfully with image id [" + imageId + "] and playerId [" + playerId + "]");
			}else{
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				responseTemplate.setMessage("Image was not found in the system against the player id [" + playerId + "] and imageId [" + imageId + "]");
			}
			
		}else{
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
			responseTemplate.setMessage("Player was not found in the system against the player id [" + playerId + "]");
		}
		
		return returnPlayer;
	}

	public GetImagesResponse getImages(Long playerId, BigDecimal pageSize, BigDecimal pageNumber, GetImagesResponse getImagesResponse) {

		List<Image> images = imageRepository.findByPlayerId(playerId);

		if (images != null && images.size() > 0) {
			// Sort the list based on posted time stamp
			images.sort((Image i1, Image i2) -> i2.getPostedTimestamp().compareTo(i1.getPostedTimestamp()));
			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), images);

			getImagesResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getImagesResponse.setMessage(paginationReturn.getReturnMessage());
			getImagesResponse.setStatus(GetImagesResponse.StatusEnum.SUCCESS);
			getImagesResponse.setPageNumber(pageNumber.longValue());
			getImagesResponse.setPageSize(pageSize.longValue());
			getImagesResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			getImagesResponse.setData(modifyPreSignedUrls((List<Image>) paginationReturn.getReturnList()));
		} else {
			getImagesResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getImagesResponse.setMessage("No records are present for the playerId [" + playerId + "]");
			getImagesResponse.setStatus(GetImagesResponse.StatusEnum.FAILURE);
			getImagesResponse.setPageNumber(pageNumber.longValue());
			getImagesResponse.setPageSize(pageSize.longValue());
			getImagesResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getImagesResponse;
	}
	
	public CreateImageResponse getImageByImageId(Long imageId, CreateImageResponse createImageResponse) {

		Image image = imageRepository.findOne(imageId);
		
		if(image != null){
			createImageResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			if (image.getImageUrl() != null && image.getImageUrl() != "") {
				String objectName = amazonClient.getObjectNameFromS3Url(image.getImageUrl());
				String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
				image.setPreSignedImageUrl(presignedUrl);
			}
			createImageResponse.setData(image);
			createImageResponse.setMessage("Image has been successfully fetched against the image id [" + imageId + "]");
			createImageResponse.setStatus(StatusEnum.SUCCESS);
		}else{
			createImageResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createImageResponse.setMessage("Image has not been successfully fetched against the image id [" + imageId + "]");
			createImageResponse.setStatus(StatusEnum.FAILURE);
		}
		
		return createImageResponse;
	}
	
	
	public ResponseTemplate applaudImageByImageId(Long imageId, ResponseTemplate responseTemplate) {

		Image image = imageRepository.findOne(imageId);
		
		if(image != null){
			image.setApplauds(image.getApplauds() + 1);
			imageRepository.save(image);
			responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			responseTemplate.setMessage("Applaud on imageId [" + imageId + "] has been increased by 1, Now, the applaud count is [" + image.getApplauds() + "]");
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
		}else{
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("No Image was found against the imageId [" + imageId + "], hence applaud has not been applied");
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
		}
		
		return responseTemplate;
	}
	
	public List<Image> modifyPreSignedUrls(List<Image> images) {

		for (Image image : images) {
			if (image.getImageUrl() != null && image.getImageUrl() != "") {
				String objectName = amazonClient.getObjectNameFromS3Url(image.getImageUrl());
				String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
				image.setPreSignedImageUrl(presignedUrl);
			}
		}

		return images;
	}

	

}
