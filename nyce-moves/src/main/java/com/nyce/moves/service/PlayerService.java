package com.nyce.moves.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyce.moves.model.Player;
import com.nyce.moves.model.PlayerRequest;
import com.nyce.moves.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	public Player addPlayer(PlayerRequest playerRequest) {

		Player playerObject = new Player();

		playerObject.setEmail(playerRequest.getEmail());
		playerObject.setFirstName(playerRequest.getFirstName());
		playerObject.setLastName(playerRequest.getLastName());
		playerObject.setPassword(playerRequest.getPassword());
		playerObject.setDisplayName(playerRequest.getDisplayName());
		playerObject.setCity(playerRequest.getCity());
		playerObject.setCountry(playerRequest.getCountry());
		playerObject.setSchool(playerRequest.getSchool());
		playerObject.setPrimarySport(playerRequest.getPrimarySport());
		playerObject.setDob(playerRequest.getDob());
		playerObject.setNumberOfConnections(0L);
		playerObject.setCreationTime(OffsetDateTime.now());
		if(playerRequest.getGender() != null && playerRequest.getGender().toString().equalsIgnoreCase(Player.GenderEnum.MALE.toString())){
			playerObject.setGender(Player.GenderEnum.MALE);
		}else if(playerRequest.getGender() != null && playerRequest.getGender().toString().equalsIgnoreCase(Player.GenderEnum.FEMALE.toString())){
			playerObject.setGender(Player.GenderEnum.FEMALE);
		}
		
		Player player = playerRepository.save(playerObject);
		return player;
	}

	public Player updatePlayer(Player player) {
		return playerRepository.save(player);
	}

	public Player loginPlayer(String username, String password) {

		List<Player> players = playerRepository.findByEmail(username);

		if (players != null && players.size() > 0) {
			String passwordInSystem = players.get(0).getPassword();
			if (passwordInSystem != null && passwordInSystem.length() > 0) {
				if (passwordInSystem.equals(password)) {
					return players.get(0);
				}
			}
		}
		return null;
	}
	
	
	public Player getPlayer(Long playerId) {
		Player player = playerRepository.findOne(playerId);
		return player;
	}

	public void deletePlayer(Long playerId) {
		playerRepository.delete(playerId);
	}

}
