package com.nyce.moves.api;

import java.io.IOException;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.PlayerRequest;
import com.nyce.moves.model.PlayerResponse;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.service.PlayerService;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

@Controller
public class PlayersApiController implements PlayersApi {

	private static final Logger log = LoggerFactory.getLogger(PlayersApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	PlayerService playerService;

	@org.springframework.beans.factory.annotation.Autowired
	public PlayersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<PlayerResponse> createPlayer(
			@ApiParam(value = "Created user object", required = true) @Valid @RequestBody PlayerRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		Player player = playerService.addPlayer(body);
		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
		playerResponse.setMessage("Player has been successfully created");
		playerResponse.setCode("11001");
		playerResponse.setData(player);
		return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> deletePlayer(
			@ApiParam(value = "The Id of the player which need to be deleted", required = true) @PathVariable("playerId") Long playerId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ResponseTemplate>(objectMapper.readValue(
						"{  \"code\" : \"code\",  \"message\" : \"message\",  \"status\" : \"SUCCESS\"}",
						ResponseTemplate.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ResponseTemplate>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		playerService.deletePlayer(playerId);
		ResponseTemplate responseTemplate = new ResponseTemplate();
		responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
		responseTemplate.setMessage("Player with playerId [" + playerId + "] has been deleted successfully");
		responseTemplate.setCode("11001");
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<PlayerResponse> getPlayer(
			@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		Player player = playerService.getPlayer(playerId);
		PlayerResponse playerResponse = new PlayerResponse();
		if (player != null) {
			playerResponse.setData(player);
			playerResponse.setCode("11001");
			playerResponse.setMessage("Player has been fetched successfully");
			playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
		} else {
			playerResponse.setData(player);
			playerResponse.setCode("41001");
			playerResponse.setMessage("Player with playerId [" + playerId + "] doesn't exists in the system");
			playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
		}

		return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
	}

	public ResponseEntity<PlayerResponse> loginUser(
			@ApiParam(value = "The user name for login", required = true) @RequestHeader(value = "username", required = true) String username,
			@ApiParam(value = "The password for login in clear text", required = true) @RequestHeader(value = "password", required = true) String password) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		Player player = playerService.loginPlayer(username, password);

		PlayerResponse playerResponse = new PlayerResponse();
		if (player != null) {
			playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
			playerResponse.setMessage("Successful Login");
			playerResponse.setCode("200");
			playerResponse.setData(player);
			return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
		} else {
			playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
			playerResponse.setMessage("Unsuccessful Login");
			playerResponse.setCode("403");
			return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<ResponseTemplate> logoutUser(
			@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Integer playerId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ResponseTemplate>(objectMapper.readValue(
						"{  \"code\" : \"code\",  \"message\" : \"message\",  \"status\" : \"SUCCESS\"}",
						ResponseTemplate.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ResponseTemplate>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ResponseTemplate>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<PlayerResponse> updatePlayer(
			@ApiParam(value = "player that need to be updated", required = true) @PathVariable("playerId") String playerId,
			@ApiParam(value = "Updated player object", required = true) @Valid @RequestBody Player body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		Player updatedPlayer = playerService.updatePlayer(body);
		PlayerResponse playerResponse = new PlayerResponse();
		if (updatedPlayer != null) {
			playerResponse.setData(updatedPlayer);
			playerResponse.setCode("11001");
			playerResponse.setMessage("Player has been updated successfully");
			playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
		} else {
			playerResponse.setCode("41001");
			playerResponse.setMessage("Player with playerId [" + playerId + "] was not updated in the system");
			playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
		}

		return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
	}

	public ResponseEntity<Void> getFollowers(
			@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Integer playerId) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Void> playerFollows(
			@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Integer playerId) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

}
