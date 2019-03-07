package com.nyce.moves.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.common.UtilityFunctions;
import com.nyce.moves.model.CreateVideoResponse;
import com.nyce.moves.model.CreateVideoResponse.StatusEnum;
import com.nyce.moves.model.GetVideosResponse;
import com.nyce.moves.model.Video;
import com.nyce.moves.model.VideoRequest;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.repository.VideoRepository;
import com.nyce.moves.repository.PlayerRepository;

@Service
public class VideoService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	VideoRepository videoRepository;

	public CreateVideoResponse addVideo(Long playerId, VideoRequest videoRequest,CreateVideoResponse createVideoResponse) {

		Video video = new Video();
		video.setApplauds(0l);
		video.setDescription(videoRequest.getDescription());
		video.setVideoUrl(videoRequest.getVideoUrl());
		video.setPlayerId(playerId);
		video.setPostedTimestamp(OffsetDateTime.now());
		video.setTitle(videoRequest.getTitle());
		long videoId = 0L;

		Player player = playerRepository.findOne(playerId);
		Player returnPlayer = null;

		if (player != null) {
			player.getVideos().add(video);
			returnPlayer = playerRepository.save(player);
			if (returnPlayer != null && returnPlayer.getVideos() != null && returnPlayer.getVideos().size() > 0) {
				videoId = returnPlayer.getVideos().get(returnPlayer.getVideos().size() - 1).getVideoId();
				video.setVideoId(videoId);
			}
			createVideoResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			createVideoResponse.setMessage("Video has been successfully submitted for player [" + playerId + "]");
			createVideoResponse.setData(video);
			createVideoResponse.setStatus(CreateVideoResponse.StatusEnum.SUCCESS);
		} else {
			createVideoResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createVideoResponse.setMessage("Video has not been successfully submitted for player [" + playerId + "], Because playerId doesn't exist");
			createVideoResponse.setStatus(CreateVideoResponse.StatusEnum.FAILURE);
		}

		return createVideoResponse;
	}

	public Player deleteVideo(Long playerId, Long videoId, ResponseTemplate responseTemplate) {
		
		Player player = playerRepository.findOne(playerId);
		Player returnPlayer = null;
		if(player != null){
			List<Video> videos = player.getVideos();
			
			boolean deleteIndicator = videos.removeIf(p -> p.getVideoId() == videoId);
			if(deleteIndicator){
				player.setVideos(videos);
				returnPlayer = playerRepository.save(player);
				videoRepository.delete(videoId);
				responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				responseTemplate.setMessage("Video has been deleted successfully with video id [" + videoId + "] and playerId [" + playerId + "]");
			}else{
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				responseTemplate.setMessage("Video was not found in the system against the player id [" + playerId + "] and videoId [" + videoId + "]");
			}
			
		}else{
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
			responseTemplate.setMessage("Player was not found in the system against the player id [" + playerId + "]");
		}
		
		return returnPlayer;
	}

	public GetVideosResponse getVideos(Long playerId, BigDecimal pageSize, BigDecimal pageNumber, GetVideosResponse getVideosResponse) {

		List<Video> videos = videoRepository.findByPlayerId(playerId);

		if (videos != null && videos.size() > 0) {
			// Sort the list based on posted time stamp
			videos.sort((Video i1, Video i2) -> i2.getPostedTimestamp().compareTo(i1.getPostedTimestamp()));
			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), videos);

			getVideosResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getVideosResponse.setMessage(paginationReturn.getReturnMessage());
			getVideosResponse.setStatus(GetVideosResponse.StatusEnum.SUCCESS);
			getVideosResponse.setPageNumber(pageNumber.longValue());
			getVideosResponse.setPageSize(pageSize.longValue());
			getVideosResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			getVideosResponse.setData((List<Video>) paginationReturn.getReturnList());
		} else {
			getVideosResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getVideosResponse.setMessage("No records are present for the playerId [" + playerId + "]");
			getVideosResponse.setStatus(GetVideosResponse.StatusEnum.FAILURE);
			getVideosResponse.setPageNumber(pageNumber.longValue());
			getVideosResponse.setPageSize(pageSize.longValue());
			getVideosResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getVideosResponse;
	}
	
	public CreateVideoResponse getVideoByVideoId(Long videoId, CreateVideoResponse createVideoResponse) {

		Video video = videoRepository.findOne(videoId);
		
		if(video != null){
			createVideoResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			createVideoResponse.setData(video);
			createVideoResponse.setMessage("Video has been successfully fetched against the video id [" + videoId + "]");
			createVideoResponse.setStatus(StatusEnum.SUCCESS);
		}else{
			createVideoResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createVideoResponse.setMessage("Video has not been successfully fetched against the video id [" + videoId + "]");
			createVideoResponse.setStatus(StatusEnum.FAILURE);
		}
		
		return createVideoResponse;
	}
	
	
	public ResponseTemplate applaudVideoByVideoId(Long videoId, ResponseTemplate responseTemplate) {

		Video video = videoRepository.findOne(videoId);
		
		if(video != null){
			video.setApplauds(video.getApplauds() + 1);
			videoRepository.save(video);
			responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			responseTemplate.setMessage("Applaud on videoId [" + videoId + "] has been increased by 1, Now, the applaud count is [" + video.getApplauds() + "]");
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
		}else{
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("No Video was found against the videoId [" + videoId + "], hence applaud has not been applied");
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
		}
		
		return responseTemplate;
	}
	

}