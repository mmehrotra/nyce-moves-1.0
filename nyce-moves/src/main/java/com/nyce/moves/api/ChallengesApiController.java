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
import com.nyce.moves.model.ChallengeRequest;
import com.nyce.moves.model.CreateChallengeResponse;
import com.nyce.moves.model.GetAllChallengesResponse;
import com.nyce.moves.model.GetChallengeResponse;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.service.ChallengeService;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-18T17:53:27.140+05:30")

@Controller
public class ChallengesApiController implements ChallengesApi {

	private static final Logger log = LoggerFactory.getLogger(ChallengesApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	ChallengeService challengeService;

	@org.springframework.beans.factory.annotation.Autowired
	public ChallengesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ResponseTemplate> closeChallenge(@ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "") @RequestHeader(value = "challengeName", required = false) String challengeName, @ApiParam(value = "") @RequestHeader(value = "challengeId", required = false) Long challengeId) {
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
		responseTemplate = challengeService.closeChallenge(identifier, challengeId, challengeName, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<GetChallengeResponse> getChallengeDetail(@ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "") @RequestHeader(value = "challengeName", required = false) String challengeName, @ApiParam(value = "") @RequestHeader(value = "challengeId", required = false) Long challengeId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetChallengeResponse>(objectMapper.readValue("\"\"", GetChallengeResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetChallengeResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		GetChallengeResponse getChannelResponse = new GetChallengeResponse();
		getChannelResponse = challengeService.getChallenge(challengeId, challengeName, getChannelResponse);
		return new ResponseEntity<GetChallengeResponse>(getChannelResponse, HttpStatus.OK);
	}

	public ResponseEntity<GetAllChallengesResponse> getChallengesOfAPlayer(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") BigDecimal pageSize, @ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue = "1") BigDecimal pageNumber, @ApiParam(value = "", allowableValues = "OPEN, CLOSED, ALL", defaultValue = "OPEN") @RequestHeader(value = "challengeStatus", required = false) String challengeStatus) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetAllChallengesResponse>(objectMapper.readValue("\"\"", GetAllChallengesResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetAllChallengesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		GetAllChallengesResponse getAllChallengesResponse = new GetAllChallengesResponse();
		getAllChallengesResponse = challengeService.getAllChallengesOfAPlayer(playerId, challengeStatus, pageSize, pageNumber, getAllChallengesResponse);
		return new ResponseEntity<GetAllChallengesResponse>(getAllChallengesResponse, HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> joinChallenge(@ApiParam(value = "playerId who want to join the challenge", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "") @RequestHeader(value = "challengeName", required = false) String challengeName, @ApiParam(value = "") @RequestHeader(value = "challengeId", required = false) Long challengeId) {
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
		responseTemplate = challengeService.joinChallenge(playerId, challengeId, challengeName, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<CreateChallengeResponse> submitChallange(@ApiParam(value = "", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "Created Challenge object", required = true) @Valid @RequestBody ChallengeRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<CreateChallengeResponse>(objectMapper.readValue("\"\"", CreateChallengeResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<CreateChallengeResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		CreateChallengeResponse createChallengeResponse = new CreateChallengeResponse();
		createChallengeResponse = challengeService.submitChallenge(playerId, body, createChallengeResponse);
		return new ResponseEntity<CreateChallengeResponse>(createChallengeResponse, HttpStatus.OK);
	}

}
