package com.nyce.moves.security;

import static com.nyce.moves.security.SecurityConstants.IDENTIFIER;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nyce.moves.common.CustomException;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
public class JwtTokenFilter extends OncePerRequestFilter {

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(httpServletRequest);

		Long identifier = 0L;

		if (httpServletRequest.getHeader(IDENTIFIER) != null) {
			identifier = new Long(httpServletRequest.getHeader(IDENTIFIER));
		}
		try {
			if (token != null && jwtTokenProvider.validateToken(token, identifier)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token, identifier);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (CustomException ex) {
			// this is very important, since it guarantees the user is not
			// authenticated at all
			SecurityContextHolder.clearContext();
			httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
			return;
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
