package com.nyce.moves.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nyce.moves.common.CustomException;
import com.nyce.moves.model.Role;
import com.nyce.moves.service.UserDetailsServiceImpl;
import static com.nyce.moves.security.SecurityConstants.PLAYER_ID;
import static com.nyce.moves.security.SecurityConstants.HEADER_STRING;
import static com.nyce.moves.security.SecurityConstants.TOKEN_PREFIX;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	/**
	 * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static
	 * key here. Ideally, in a microservices environment, this key would be kept
	 * on a config-server.
	 */
	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds; // 1h

	@Autowired
	private UserDetailsServiceImpl myUserDetails;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username, List<Role> roles, Long identifier) {

		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
		claims.put(PLAYER_ID, identifier);

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey + 1)//
				.compact();
	}

	public Authentication getAuthentication(String token, Long identifier) {
		UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token, identifier));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token, Long identifier) {
		return Jwts.parser().setSigningKey(secretKey + identifier).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(HEADER_STRING);
		if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token, Long identifier) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey + identifier).parseClaimsJws(token);

			if (claims.getBody() != null && claims.getBody().get(PLAYER_ID) != null) {
				Object playerIdObject = claims.getBody().get(PLAYER_ID);

				if (playerIdObject != null) {
					Long playerId = new Long(playerIdObject.toString());
					if (playerId.compareTo(identifier) != 0) {
						throw new JwtException(token); 
					}
				}
			}

			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
