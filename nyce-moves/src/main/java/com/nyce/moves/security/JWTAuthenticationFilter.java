package com.nyce.moves.security;

import static com.nyce.moves.security.SecurityConstants.EXPIRATION_TIME;
import static com.nyce.moves.security.SecurityConstants.HEADER_STRING;
import static com.nyce.moves.security.SecurityConstants.SECRET;
import static com.nyce.moves.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyce.moves.model.PlayerRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			PlayerRequest creds = (PlayerRequest) new ObjectMapper().readValue(req.getInputStream(), PlayerRequest.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}