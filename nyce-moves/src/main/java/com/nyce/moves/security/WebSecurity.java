package com.nyce.moves.security;

import static com.nyce.moves.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http.csrf().disable().authorizeRequests()
		 //.antMatchers(HttpMethod.GET, "**/players/login").permitAll()
		 .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
		 .antMatchers(HttpMethod.GET, "/players/login").permitAll()
		 .anyRequest().authenticated()
		 .and()
         //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
         //.addFilter(new JWTAuthorizationFilter(authenticationManager()));	
	     // We filter the api/login requests
         .addFilterBefore(new JWTLoginFilter("/players/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		 //.addFilterBefore(new JWTLoginFilter("/players/login", authenticationManager()), JWTAuthenticationFilter.class)
         // And filter other requests to check the presence of JWT in header
         //.addFilter(new JWTAuthenticationFilter(authenticationManager()));
         .addFilterBefore(new JWTAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
	
	}
	

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}