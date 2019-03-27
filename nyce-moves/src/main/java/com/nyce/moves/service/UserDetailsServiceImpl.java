package com.nyce.moves.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nyce.moves.model.Player;
import com.nyce.moves.model.Role;
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
		//return new User(player.getEmail(), player.getPassword(), emptyList());
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ROLE_ADMIN);
		roles.add(Role.ROLE_CLIENT);		
		
		return org.springframework.security.core.userdetails.User//
		        .withUsername(username)//
		        .password(player.getPassword())//
		        .authorities(roles)//
		        .accountExpired(false)//
		        .accountLocked(false)//
		        .credentialsExpired(false)//
		        .disabled(false)//
		        .build();
		
		
	}
}