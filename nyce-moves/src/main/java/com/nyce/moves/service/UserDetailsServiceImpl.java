package com.nyce.moves.service;

import static java.util.Collections.emptyList;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nyce.moves.model.Player;
import com.nyce.moves.repository.PlayerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private PlayerRepository playerRepository;

	public UserDetailsServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Player> players = playerRepository.findByEmail(username);
		if (players == null || players.size() == 0) {
			throw new UsernameNotFoundException(username);
		}
		Player player = players.get(0);
		return new User(player.getEmail(), player.getPassword(), emptyList());
	}
}