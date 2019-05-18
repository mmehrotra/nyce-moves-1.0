package com.nyce.moves.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.common.UtilityFunctions;
import com.nyce.moves.model.Challenge;
import com.nyce.moves.model.GetAllPostsResponse;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.Post;
import com.nyce.moves.model.PostRequest;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.repository.PlayerRepository;
import com.nyce.moves.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PlayerService playerService;

	public Post addPost(Long playerId, PostRequest postRequest) {

		Post post = new Post();
		post.setPost(postRequest.getPost());
		post.setPostedBy(playerId);
		post.setPostedTimestamp(new java.sql.Timestamp(new java.util.Date().getTime()));
		post.setApplauds(0L);
		long postId = 0L;

		Player player = playerRepository.findOne(playerId);
		Player returnPlayer = null;

		if (player != null) {
			
			if(postRequest.getChallengeName() != null && postRequest.getChallengeName().trim() != ""){
				post.setChallengeName(postRequest.getChallengeName());
				Challenge challenge = playerService.createChallenge(postRequest.getChallengeName(), player);
				post.setChallengeId(challenge.getChallangeId());				
			}
			
			player.getPosts().add(post);
			returnPlayer = playerRepository.save(player);
			if (returnPlayer != null && returnPlayer.getPosts() != null && returnPlayer.getPosts().size() > 0) {
				postId = returnPlayer.getPosts().get(returnPlayer.getPosts().size() - 1).getPostId();
				post.setPostId(postId);
			}
		} else {
			post = null;
		}

		return post;
	}

	public Player deletePost(Long playerId, Long postId, ResponseTemplate responseTemplate) {

		Player player = playerRepository.findOne(playerId);
		Player returnPlayer = null;
		if (player != null) {
			List<Post> posts = player.getPosts();

			boolean deleteIndicator = posts.removeIf(p -> p.getPostId() == postId);
			if (deleteIndicator) {
				player.setPosts(posts);
				returnPlayer = playerRepository.save(player);
				postRepository.delete(postId);
				responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				responseTemplate.setMessage("Post has been deleted successfully with post id [" + postId + "] and playerId [" + playerId + "]");
			} else {
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				responseTemplate.setMessage("Post was not found in the system against the player id [" + playerId + "] and postId [" + postId + "]");
			}

		} else {
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
			responseTemplate.setMessage("Player was not found in the system against the player id [" + playerId + "]");
		}

		return returnPlayer;
	}

	public GetAllPostsResponse getPosts(Long playerId, BigDecimal pageSize, BigDecimal pageNumber, GetAllPostsResponse getAllPostsResponse) {

		List<Post> posts = postRepository.findByPostedBy(playerId);

		if (posts != null && posts.size() > 0) {
			// Sort the list based on posted time stamp
			posts.sort((Post p1, Post p2) -> p2.getPostedTimestamp().compareTo(p1.getPostedTimestamp()));
			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), posts);

			getAllPostsResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getAllPostsResponse.setMessage(paginationReturn.getReturnMessage());
			getAllPostsResponse.setStatus(GetAllPostsResponse.StatusEnum.SUCCESS);
			getAllPostsResponse.setPageNumber(pageNumber.longValue());
			getAllPostsResponse.setPageSize(pageSize.longValue());
			getAllPostsResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			getAllPostsResponse.setData((List<Post>) paginationReturn.getReturnList());
		} else {
			getAllPostsResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getAllPostsResponse.setMessage("No records are present for the playerId [" + playerId + "]");
			getAllPostsResponse.setStatus(GetAllPostsResponse.StatusEnum.FAILURE);
			getAllPostsResponse.setPageNumber(pageNumber.longValue());
			getAllPostsResponse.setPageSize(pageSize.longValue());
			getAllPostsResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getAllPostsResponse;
	}

	public ResponseTemplate applaudPostByPostId(Long postId, Long playerId, ResponseTemplate responseTemplate, String unapplaud) {

		Post post = postRepository.findOne(postId);

		if (post != null) {
			if (unapplaud != null && unapplaud.equalsIgnoreCase("true")) {
				List<Long> players = post.getApplaudDoneByPlayerIds();
				if (players != null && players.size() > 0 && players.contains(playerId)) {
					players.remove(playerId);
					post.setApplauds(post.getApplauds() - 1);
					post.setApplaudDoneByPlayerIds(players);
					postRepository.save(post);
					responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					responseTemplate.setMessage("Applaud on postId [" + postId + "] has been decreased by 1, Now, the applaud count is [" + post.getApplauds() + "]");
					responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				} else {
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Current player [" + playerId + " ] is not in the list of players who have applauded the post [" + postId + "]");
					responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				}
			} else {
				post.setApplauds(post.getApplauds() + 1);
				List<Long> players = post.getApplaudDoneByPlayerIds();
				if (players == null) {
					players = new ArrayList<Long>();
				}
				if (!players.contains(playerId)) {
					players.add(playerId);
					post.setApplaudDoneByPlayerIds(players);
					postRepository.save(post);
					responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					responseTemplate.setMessage("Applaud on postId [" + postId + "] has been increased by 1, Now, the applaud count is [" + post.getApplauds() + "]");
					responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				} else {
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Current player [" + playerId + " ] has already appluaded the post [" + postId + "], hence applaud has not been applied");
					responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				}
			}

		} else {
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("No Post was found against the postId [" + postId + "], hence applaud has not been applied");
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
		}

		return responseTemplate;
	}

}
