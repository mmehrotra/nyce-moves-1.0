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
import com.nyce.moves.model.CreateImageResponse;
import com.nyce.moves.model.CreateVideoResponse;
import com.nyce.moves.model.GetImagesResponse;
import com.nyce.moves.model.GetVideosResponse;
import com.nyce.moves.model.Image;
import com.nyce.moves.model.ImageRequest;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.VideoRequest;
import com.nyce.moves.service.ImageService;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

@Controller
public class MediaApiController implements MediaApi {

    private static final Logger log = LoggerFactory.getLogger(MediaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private ImageService imageService;

    @org.springframework.beans.factory.annotation.Autowired
    public MediaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ResponseTemplate> applaudImageByImageId(@ApiParam(value = "The ImageId of the image",required=true) @PathVariable("imageId") Long imageId) {
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
        imageService.applaudImageByImageId(imageId, responseTemplate);
        return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
    }

    public ResponseEntity<ResponseTemplate> applaudVideosByVideoId(@ApiParam(value = "The video id of the video which need to be fetched",required=true) @PathVariable("videoId") Long videoId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ResponseTemplate>(objectMapper.readValue("{  \"code\" : \"code\",  \"message\" : \"message\",  \"status\" : \"SUCCESS\"}", ResponseTemplate.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ResponseTemplate>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ResponseTemplate>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ResponseTemplate> deleteImage(@ApiParam(value = "The playerId for which post needs to be deleted",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "" ,required=true) @RequestHeader(value="imageId", required=true) Long imageId) {
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
		imageService.deleteImage(playerId, imageId, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
    }

    public ResponseEntity<ResponseTemplate> deleteVideo(@ApiParam(value = "The playerId for which post needs to be deleted",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "" ,required=true) @RequestHeader(value="videoId", required=true) Long videoId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ResponseTemplate>(objectMapper.readValue("{  \"code\" : \"code\",  \"message\" : \"message\",  \"status\" : \"SUCCESS\"}", ResponseTemplate.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ResponseTemplate>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ResponseTemplate>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetImagesResponse> getImages(@ApiParam(value = "The playerId of the current player",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") BigDecimal pageSize,@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue="1") BigDecimal pageNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetImagesResponse>(objectMapper.readValue("\"\"", GetImagesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetImagesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        GetImagesResponse getImagesReponse = new GetImagesResponse();
		getImagesReponse = imageService.getImages(playerId, pageSize, pageNumber, getImagesReponse);

		return new ResponseEntity<GetImagesResponse>(getImagesReponse, HttpStatus.OK);
    }

    public ResponseEntity<CreateImageResponse> getImagesByImageId(@ApiParam(value = "The ImageId of the image",required=true) @PathVariable("imageId") Long imageId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateImageResponse>(objectMapper.readValue("\"\"", CreateImageResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateImageResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        CreateImageResponse createImageResponse = new CreateImageResponse();
        createImageResponse = imageService.getImageByImageId(imageId, createImageResponse);

        return new ResponseEntity<CreateImageResponse>(createImageResponse, HttpStatus.OK);
    }

    public ResponseEntity<GetVideosResponse> getVideos(@ApiParam(value = "The playerId of the current player",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") BigDecimal pageSize,@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue="1") BigDecimal pageNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetVideosResponse>(objectMapper.readValue("\"\"", GetVideosResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetVideosResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetVideosResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CreateVideoResponse> getVideosByVideoId(@ApiParam(value = "The video id of the video which need to be fetched",required=true) @PathVariable("videoId") Long videoId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateVideoResponse>(objectMapper.readValue("\"\"", CreateVideoResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateVideoResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateVideoResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CreateImageResponse> submitImage(@ApiParam(value = "",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "Created image object" ,required=true )  @Valid @RequestBody ImageRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateImageResponse>(objectMapper.readValue("\"\"", CreateImageResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateImageResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        CreateImageResponse createImageResponse = new CreateImageResponse();
        createImageResponse = imageService.addImage(playerId, body, createImageResponse);
		return new ResponseEntity<CreateImageResponse>(createImageResponse, HttpStatus.OK);
    }

    public ResponseEntity<CreateVideoResponse> submitVideo(@ApiParam(value = "",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "Created Video object" ,required=true )  @Valid @RequestBody VideoRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateVideoResponse>(objectMapper.readValue("\"\"", CreateVideoResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateVideoResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateVideoResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
