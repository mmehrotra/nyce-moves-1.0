package com.nyce.moves.security;

import static com.nyce.moves.security.SecurityConstants.EXPIRATION_TIME;
import static com.nyce.moves.security.SecurityConstants.HEADER_STRING;
import static com.nyce.moves.security.SecurityConstants.SECRET;
import static com.nyce.moves.security.SecurityConstants.TOKEN_PREFIX;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {


	public void addAuthentication(HttpServletResponse response, String username) {
		// We generate a token now.
		String JWT = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
			if (username != null) // we managed to retrieve a user
			{
				//return new Authentication(username);
			}
		}
		return null;
	}
}
