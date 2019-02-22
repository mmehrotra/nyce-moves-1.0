package com.nyce.moves.api;

import java.math.BigDecimal;
import com.nyce.moves.model.ImageRequest;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.VideoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

@Controller
public class MediaApiController implements MediaApi {

    private static final Logger log = LoggerFactory.getLogger(MediaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MediaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ResponseTemplate> applaudImageByImageId(@ApiParam(value = "The ImageId of the image",required=true) @PathVariable("imageId") Integer imageId) {
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

    public ResponseEntity<ResponseTemplate> applaudVideosByVideoId(@ApiParam(value = "The video id of the video which need to be fetched",required=true) @PathVariable("videoId") Integer videoId) {
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

    public ResponseEntity<ResponseTemplate> deleteImage(@ApiParam(value = "The playerId for which post needs to be deleted",required=true) @PathVariable("playerId") String playerId,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "imageId", required = true) String imageId) {
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

    public ResponseEntity<ResponseTemplate> deleteVideo(@ApiParam(value = "The playerId for which post needs to be deleted",required=true) @PathVariable("playerId") String playerId,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "videoId", required = true) String videoId) {
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

    public ResponseEntity<Void> getImages(@ApiParam(value = "The playerId of the current player",required=true) @PathVariable("playerId") Integer playerId,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") BigDecimal pageSize,@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue="1") BigDecimal pageNumber) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getImagesByImageId(@ApiParam(value = "The ImageId of the image",required=true) @PathVariable("imageId") Integer imageId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getVideos(@ApiParam(value = "The playerId of the current player",required=true) @PathVariable("playerId") Integer playerId,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") BigDecimal pageSize,@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue="1") BigDecimal pageNumber) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getVideosByVideoId(@ApiParam(value = "The video id of the video which need to be fetched",required=true) @PathVariable("videoId") Integer videoId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> submitImage(@ApiParam(value = "",required=true) @PathVariable("playerId") Integer playerId,@ApiParam(value = "Created image object" ,required=true )  @Valid @RequestBody ImageRequest body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> submitVideo(@ApiParam(value = "",required=true) @PathVariable("playerId") Integer playerId,@ApiParam(value = "Created Video object" ,required=true )  @Valid @RequestBody VideoRequest body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
