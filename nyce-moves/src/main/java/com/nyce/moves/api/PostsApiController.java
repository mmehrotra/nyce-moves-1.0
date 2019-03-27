package com.nyce.moves.api;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.model.CreatePostResponse;
import com.nyce.moves.model.CreatePostResponse.StatusEnum;
import com.nyce.moves.model.GetAllPostsResponse;
import com.nyce.moves.model.Post;
import com.nyce.moves.model.PostRequest;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.service.PostService;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

@Controller
public class PostsApiController implements PostsApi {

	private static final Logger log = LoggerFactory.getLogger(PostsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private PostService postService;

	@org.springframework.beans.factory.annotation.Autowired
	public PostsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ResponseTemplate> deletePost(@ApiParam(value = "The playerId for which post needs to be deleted", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "", required = true) @RequestHeader(value = "postId", required = true) Long postId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ResponseTemplate>(objectMapper.readValue("{  \"code\" : \"code\",  \"message\" : \"message\",  \"status\" : \"SUCCESS\"}", ResponseTemplate.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ResponseTemplate>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		ResponseTemplate responseTemplate = new ResponseTemplate();
		postService.deletePost(playerId, postId, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<GetAllPostsResponse> getPosts(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") BigDecimal pageSize, @ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue = "1") BigDecimal pageNumber) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetAllPostsResponse>(objectMapper.readValue("\"\"", GetAllPostsResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetAllPostsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		GetAllPostsResponse getAllPostsReponse = new GetAllPostsResponse();
		getAllPostsReponse = postService.getPosts(playerId, pageSize, pageNumber, getAllPostsReponse);

		return new ResponseEntity<GetAllPostsResponse>(getAllPostsReponse, HttpStatus.OK);
	}

	public ResponseEntity<CreatePostResponse> submitPost(@ApiParam(value = "", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "Created post object", required = true) @Valid @RequestBody PostRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<CreatePostResponse>(objectMapper.readValue("\"\"", CreatePostResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<CreatePostResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		CreatePostResponse createPostResponse = new CreatePostResponse();
		Post post = postService.addPost(playerId, body);

		if (post != null) {
			createPostResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			createPostResponse.setMessage("Post has been successfully submitted for player [" + playerId + "]");
			createPostResponse.setData(post);
			createPostResponse.setStatus(StatusEnum.SUCCESS);
		} else {
			createPostResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createPostResponse.setMessage("Post has not been successfully submitted for player [" + playerId + "]");
			createPostResponse.setStatus(StatusEnum.FAILURE);
		}

		return new ResponseEntity<CreatePostResponse>(createPostResponse, HttpStatus.OK);
	}

}
