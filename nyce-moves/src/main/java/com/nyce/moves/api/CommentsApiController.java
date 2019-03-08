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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyce.moves.model.CommentsRequest;
import com.nyce.moves.model.CreateCommentResponse;
import com.nyce.moves.model.GetCommentsResponse;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.service.CommentsService;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

@Controller
public class CommentsApiController implements CommentsApi {

    private static final Logger log = LoggerFactory.getLogger(CommentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private CommentsService commentsService;

    @org.springframework.beans.factory.annotation.Autowired
    public CommentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ResponseTemplate> deleteComment(@ApiParam(value = "The playerId for which comment needs to be deleted" ,required=true) @RequestHeader(value="playerId", required=true) Long playerId,@ApiParam(value = "" ,required=true) @RequestHeader(value="commentId", required=true) Long commentId) {
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
        responseTemplate = commentsService.deleteComments(playerId, commentId, responseTemplate);
        return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
    }

    public ResponseEntity<GetCommentsResponse> getComments(@ApiParam(value = "The playerId of the current player" ,required=true) @RequestHeader(value="playerId", required=true) Long playerId,@ApiParam(value = "ImageId for which comments need to be fetched" ) @RequestHeader(value="imageId", required=false) Long imageId,@ApiParam(value = "VideoId for which comments need to be fetched" ) @RequestHeader(value="videoId", required=false) Long videoId,@ApiParam(value = "PostId for which comments need to be fetched" ) @RequestHeader(value="postId", required=false) Long postId,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") BigDecimal pageSize,@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue="1") BigDecimal pageNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCommentsResponse>(objectMapper.readValue("\"\"", GetCommentsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCommentsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        GetCommentsResponse getCommentsResponse =  new GetCommentsResponse();
        getCommentsResponse = commentsService.getComments(playerId, imageId, videoId, postId, pageSize, pageNumber, getCommentsResponse);
        return new ResponseEntity<GetCommentsResponse>(getCommentsResponse, HttpStatus.OK);
    }

    public ResponseEntity<CreateCommentResponse> submitComment(@ApiParam(value = "" ,required=true) @RequestHeader(value="playerId", required=true) Long playerId,@ApiParam(value = "Created Comment object" ,required=true )  @Valid @RequestBody CommentsRequest body,@ApiParam(value = "" ) @RequestHeader(value="imageId", required=false) Long imageId,@ApiParam(value = "" ) @RequestHeader(value="videoId", required=false) Long videoId,@ApiParam(value = "" ) @RequestHeader(value="postId", required=false) Long postId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateCommentResponse>(objectMapper.readValue("\"\"", CreateCommentResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateCommentResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        CreateCommentResponse createCommentResponse = new CreateCommentResponse();
        createCommentResponse = commentsService.addComments(playerId, imageId, videoId, postId, body, createCommentResponse);
        return new ResponseEntity<CreateCommentResponse>(createCommentResponse,HttpStatus.OK);
    }

}
