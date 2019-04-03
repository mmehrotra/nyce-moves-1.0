package com.nyce.moves.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyce.moves.model.ChangeEmailRequest;
import com.nyce.moves.model.ChangePasswordRequest;
import com.nyce.moves.model.GetDashBoardResponse;
import com.nyce.moves.model.GetFriendsResponse;
import com.nyce.moves.model.GetPendingFriendsRequestsResponse;
import com.nyce.moves.model.PlayerRequest;
import com.nyce.moves.model.PlayerResponse;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.Role;
import com.nyce.moves.model.UpdatePlayerRequest;
import com.nyce.moves.security.JwtTokenProvider;
import com.nyce.moves.service.PlayerService;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

@Controller
public class PlayersApiController implements PlayersApi {

	private static final Logger log = LoggerFactory.getLogger(PlayersApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	PlayerService playerService;

	@org.springframework.beans.factory.annotation.Autowired
	public PlayersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ResponseTemplate> approveFriendRequest(@ApiParam(value = "The playerId of the current player who is sending the request", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "PlayerId of the player with whom friendship is been seeken", required = true) @RequestHeader(value = "friendId", required = true) Long friendId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
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
		responseTemplate = playerService.approveFriendRequest(playerId, friendId, responseTemplate);		
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> changeEmailAddress(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "Created user object", required = true) @Valid @RequestBody ChangeEmailRequest body) {
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
		responseTemplate = playerService.changePrimaryEmail(playerId, body, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> changePassword(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @PathVariable("identifier") Long identifier, @ApiParam(value = "Change Password", required = true) @Valid @RequestBody ChangePasswordRequest body) {
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
		responseTemplate = playerService.changePassword(playerId, body, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<PlayerResponse> createPlayer(@ApiParam(value = "Created user object", required = true) @Valid @RequestBody PlayerRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse = playerService.addPlayer(body, playerResponse);
		return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> deletePlayer(@ApiParam(value = "The Id of the player which need to be deleted", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
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
		responseTemplate = playerService.deletePlayer(playerId, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}
	
	public ResponseEntity<GetDashBoardResponse> getDashboard(@ApiParam(value = "The playerId of the current player",required=true) @PathVariable("playerId") Long playerId,@ApiParam(value = "The playerId of the current player" ,required=true) @RequestHeader(value="identifier", required=true) Long identifier,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") BigDecimal pageSize,@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue="1") BigDecimal pageNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetDashBoardResponse>(objectMapper.readValue("\"\"", GetDashBoardResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetDashBoardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        GetDashBoardResponse getDashboardResponse = new GetDashBoardResponse();
		getDashboardResponse = playerService.getDashboard(playerId, pageSize, pageNumber, getDashboardResponse);
		return new ResponseEntity<GetDashBoardResponse>(getDashboardResponse, HttpStatus.OK);
    }

	public ResponseEntity<GetFriendsResponse> getFriends(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") BigDecimal pageSize, @ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue = "1") BigDecimal pageNumber) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetFriendsResponse>(objectMapper.readValue("\"\"", GetFriendsResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetFriendsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		GetFriendsResponse getFriendsResponse = new GetFriendsResponse();
		getFriendsResponse = playerService.getFriends(playerId, pageSize, pageNumber, getFriendsResponse);
		return new ResponseEntity<GetFriendsResponse>(getFriendsResponse, HttpStatus.OK);
	}

	public ResponseEntity<GetPendingFriendsRequestsResponse> getPendingFriendRequests(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetPendingFriendsRequestsResponse>(objectMapper.readValue("\"\"", GetPendingFriendsRequestsResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetPendingFriendsRequestsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		GetPendingFriendsRequestsResponse getPendingFriendsRequestsResponse = new GetPendingFriendsRequestsResponse();
		getPendingFriendsRequestsResponse = playerService.getPendingFriendsRequest(playerId, getPendingFriendsRequestsResponse);
		return new ResponseEntity<GetPendingFriendsRequestsResponse>(getPendingFriendsRequestsResponse, HttpStatus.OK);
	}

	public ResponseEntity<PlayerResponse> getPlayer(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse = playerService.getPlayer(playerId, playerResponse);
		return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
	}

	public ResponseEntity<GetDashBoardResponse> getTimeline(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") BigDecimal pageSize, @ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "pageNumber", required = false, defaultValue = "1") BigDecimal pageNumber) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetDashBoardResponse>(objectMapper.readValue("\"\"", GetDashBoardResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetDashBoardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		GetDashBoardResponse getDashboardResponse = new GetDashBoardResponse();
		getDashboardResponse = playerService.getTimeline(playerId, pageSize, pageNumber, getDashboardResponse);
		return new ResponseEntity<GetDashBoardResponse>(getDashboardResponse, HttpStatus.OK);
	}

	public ResponseEntity<PlayerResponse> loginUser(@ApiParam(value = "The user name for login", required = true) @RequestHeader(value = "username", required = true) String username, @ApiParam(value = "The password for login in clear text", required = true) @RequestHeader(value = "password", required = true) String password) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse = playerService.loginPlayer(username, password, playerResponse);

		if (playerResponse.getStatus().toString().equalsIgnoreCase(PlayerResponse.StatusEnum.SUCCESS.toString())) {
			HttpHeaders headers = new HttpHeaders();
			List<Role> roles = new ArrayList<Role>();
			roles.add(Role.ROLE_ADMIN);
			roles.add(Role.ROLE_CLIENT);
			String jwtToken = jwtTokenProvider.createToken(username, roles, playerResponse.getData().getPlayerId());
			headers.add("Authorization", "Bearer " + jwtToken);
			return new ResponseEntity<PlayerResponse>(playerResponse, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<ResponseTemplate> logoutUser(@ApiParam(value = "The playerId of the current player", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ResponseTemplate>(objectMapper.readValue("{  \"code\" : \"code\",  \"message\" : \"message\",  \"status\" : \"SUCCESS\"}", ResponseTemplate.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ResponseTemplate>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ResponseTemplate>(HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> sendFriendRequest(@ApiParam(value = "The playerId of the current player who is sending the request", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "PlayerId of the player with whom friendship is been seeken", required = true) @RequestHeader(value = "friendId", required = true) Long friendId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier) {
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
		responseTemplate = playerService.sendFriendRequest(playerId, friendId, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);
	}

	public ResponseEntity<ResponseTemplate> resetPassword(@ApiParam(value = "The user name for login", required = true) @RequestHeader(value = "username", required = true) String username) {
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
		responseTemplate = playerService.resetPassword(username, responseTemplate);
		return new ResponseEntity<ResponseTemplate>(responseTemplate, HttpStatus.OK);

	}

	public ResponseEntity<PlayerResponse> updatePlayer(@ApiParam(value = "player that need to be updated", required = true) @PathVariable("playerId") Long playerId, @ApiParam(value = "The playerId of the current player", required = true) @RequestHeader(value = "identifier", required = true) Long identifier, @ApiParam(value = "Updated player object", required = true) @Valid @RequestBody UpdatePlayerRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<PlayerResponse>(objectMapper.readValue("\"\"", PlayerResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<PlayerResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse = playerService.updatePlayer(body, playerId, playerResponse);
		return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);
	}

}
