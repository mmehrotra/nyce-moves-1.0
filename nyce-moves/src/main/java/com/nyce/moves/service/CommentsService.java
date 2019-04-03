package com.nyce.moves.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.common.UtilityFunctions;
import com.nyce.moves.model.Comments;
import com.nyce.moves.model.CommentsRequest;
import com.nyce.moves.model.CreateCommentResponse;
import com.nyce.moves.model.GetCommentsResponse;
import com.nyce.moves.model.Image;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.Post;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.ResponseTemplate.StatusEnum;
import com.nyce.moves.model.Video;
import com.nyce.moves.repository.CommentsRepository;
import com.nyce.moves.repository.ImageRepository;
import com.nyce.moves.repository.PlayerRepository;
import com.nyce.moves.repository.PostRepository;
import com.nyce.moves.repository.VideoRepository;

@Service
public class CommentsService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	CommentsRepository commentsRepository;

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	private AmazonClient amazonClient;

	public CreateCommentResponse addComments(Long playerId, Long imageId, Long videoId, Long postId, CommentsRequest commentsRequest, CreateCommentResponse createCommentResponse) {

		Comments comments = new Comments();
		comments.setComments(commentsRequest.getComments());
		comments.setPostedTimestamp(OffsetDateTime.now());
		comments.setPostedBy(playerId);
		long commentsId = 0L;

		if (imageId != null) {
			comments.setImageId(imageId);
			Image image = imageRepository.findOne(imageId);
			if (image != null) {
				image.getComments().add(comments);
				Image returnImage = imageRepository.save(image);
				commentsId = returnImage.getComments().get(returnImage.getComments().size() - 1).getCommentId();
				comments.setCommentId(commentsId);
				createCommentResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				createCommentResponse.setMessage("Comments has been successfully submitted for image [" + imageId + "] by player [" + playerId + "]");
				createCommentResponse.setData(comments);
				createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.SUCCESS);
			} else {
				createCommentResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
				createCommentResponse.setMessage("Comments has not been successfully submitted by player [" + playerId + "], Because ImageId doesn't exist");
				createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.FAILURE);
			}
		} else if (videoId != null) {
			comments.setVideoId(videoId);
			Video video = videoRepository.findOne(videoId);
			if (video != null) {
				video.getComments().add(comments);
				Video returnVideo = videoRepository.save(video);
				commentsId = returnVideo.getComments().get(returnVideo.getComments().size() - 1).getCommentId();
				comments.setCommentId(commentsId);
				createCommentResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				createCommentResponse.setMessage("Comments has been successfully submitted for video [" + videoId + "] by player [" + playerId + "]");
				createCommentResponse.setData(comments);
				createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.SUCCESS);
			} else {
				createCommentResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
				createCommentResponse.setMessage("Comments has not been successfully submitted by player [" + playerId + "], Because VideoId doesn't exist");
				createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.FAILURE);
			}
		} else if (postId != null) {
			comments.setPostId(postId);
			Post post = postRepository.findOne(postId);
			if (post != null) {
				post.getComments().add(comments);
				Post returnPost = postRepository.save(post);
				commentsId = returnPost.getComments().get(returnPost.getComments().size() - 1).getCommentId();
				comments.setCommentId(commentsId);
				createCommentResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				createCommentResponse.setMessage("Comments has been successfully submitted for post [" + postId + "] by player [" + playerId + "]");
				createCommentResponse.setData(comments);
				createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.SUCCESS);
			} else {
				createCommentResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
				createCommentResponse.setMessage("Comments has not been successfully submitted by player [" + playerId + "], Because PostId doesn't exist");
				createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.FAILURE);
			}
		} else {
			createCommentResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createCommentResponse.setMessage("Comments has not been successfully submitted by player [" + playerId + "], Because none of these provided in the request - imageId, videoId, postId");
			createCommentResponse.setStatus(CreateCommentResponse.StatusEnum.FAILURE);
		}

		return createCommentResponse;
	}

	public ResponseTemplate deleteComments(Long playerId, Long commentsId, ResponseTemplate responseTemplate) {

		Comments comment = commentsRepository.findOne(commentsId);
		
		if(comment != null){
			if(comment.getImageId() != null){
				Image image = imageRepository.findOne(comment.getImageId());
				List<Comments> comments = image.getComments();
				boolean deleteIndicator = comments.removeIf(p -> p.getCommentId() == commentsId);
				if(deleteIndicator){
					image.setComments(comments);
		            Image returnImage = imageRepository.save(image);
		            commentsRepository.delete(commentsId);
					if(returnImage != null){
						responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
						responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has been deleted against the image id [" + comment.getImageId() + "]");
						responseTemplate.setStatus(StatusEnum.SUCCESS);
					}else{
						responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
						responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has not been deleted against the image id [" + comment.getImageId() + "]");
						responseTemplate.setStatus(StatusEnum.FAILURE);
					}
				}else{
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has not been deleted against the image id [" + comment.getImageId() + "]");
					responseTemplate.setStatus(StatusEnum.FAILURE);
				}				
			}else if(comment.getVideoId() != null){
				Video video = videoRepository.findOne(comment.getVideoId());
				List<Comments> comments = video.getComments();
				boolean deleteIndicator = comments.removeIf(p -> p.getCommentId() == commentsId);
				if(deleteIndicator){
					video.setComments(comments);
		            Video returnVideo = videoRepository.save(video);
		            commentsRepository.delete(commentsId);
					if(returnVideo != null){
						responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
						responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has been deleted against the video id [" + comment.getVideoId() + "]");
						responseTemplate.setStatus(StatusEnum.SUCCESS);
					}else{
						responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
						responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has not been deleted against the video id [" + comment.getVideoId() + "]");
						responseTemplate.setStatus(StatusEnum.FAILURE);
					}
				}else{
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has not been deleted against the video id [" + comment.getVideoId() + "]");
					responseTemplate.setStatus(StatusEnum.FAILURE);
				}
				
			}else if(comment.getPostId() != null){
				Post post = postRepository.findOne(comment.getPostId());
				List<Comments> comments = post.getComments();
				boolean deleteIndicator = comments.removeIf(p -> p.getCommentId() == commentsId);
				if(deleteIndicator){
					post.setComments(comments);
		            Post returnPost = postRepository.save(post);
		            commentsRepository.delete(commentsId);
					if(returnPost != null){
						responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
						responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has been deleted against the post id [" + comment.getPostId() + "]");
						responseTemplate.setStatus(StatusEnum.SUCCESS);
					}else{
						responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
						responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has not been deleted against the post id [" + comment.getPostId() + "]");
						responseTemplate.setStatus(StatusEnum.FAILURE);
					}
				}else{
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Comment with comment id [" + commentsId + "] has not been deleted against the post id [" + comment.getPostId() + "]");
					responseTemplate.setStatus(StatusEnum.FAILURE);
				}
			}
		}
		
		return responseTemplate;
	}

	public GetCommentsResponse getComments(Long playerId, Long imageId, Long videoId, Long postId, BigDecimal pageSize, BigDecimal pageNumber, GetCommentsResponse getCommentsResponse) {

		List<Comments> comments = null;
		
		if(imageId != null){
			comments = commentsRepository.findByImageId(imageId);
		}else if(videoId != null){
			comments = commentsRepository.findByVideoId(videoId);
		}else if(postId != null){
			comments = commentsRepository.findByPostId(postId);
		}
		
		if(comments != null && comments.size() > 0){
			// Sort the list based on posted time stamp
			comments.sort((Comments i1, Comments i2) -> i2.getPostedTimestamp().compareTo(i1.getPostedTimestamp()));
			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), comments);
			getCommentsResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getCommentsResponse.setMessage(paginationReturn.getReturnMessage());
			getCommentsResponse.setStatus(GetCommentsResponse.StatusEnum.SUCCESS);
			getCommentsResponse.setPageNumber(pageNumber.longValue());
			getCommentsResponse.setPageSize(pageSize.longValue());
			getCommentsResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			List<Comments> returnComments = (List<Comments>) paginationReturn.getReturnList();
			
			for(Comments comment : returnComments){
				Long commentPostedBy = comment.getPostedBy();
				Player player = playerRepository.findOne(commentPostedBy);
				if(player != null){
					comment.setDisplayName(player.getDisplayName());
					comment.setProfileImageUrl(player.getProfileImageUrl());
					comment.setProfilePreSignUrl(amazonClient.getPreSignUrlFromUrl(player.getProfileImageUrl()));
				}
			}
			getCommentsResponse.setData(returnComments);
		}else{
			getCommentsResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getCommentsResponse.setMessage("No records are present for the imageId [" + imageId + "], videoId [" + videoId + "], postId [" + postId + "]");
			getCommentsResponse.setStatus(GetCommentsResponse.StatusEnum.FAILURE);
			getCommentsResponse.setPageNumber(pageNumber.longValue());
			getCommentsResponse.setPageSize(pageSize.longValue());
			getCommentsResponse.setTotalNumberofPagesAvailable(0l);
		}
		
		return getCommentsResponse;
	}

}
