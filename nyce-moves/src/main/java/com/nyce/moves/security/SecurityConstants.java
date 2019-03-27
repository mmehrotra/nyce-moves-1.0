package com.nyce.moves.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/players";
    public static final String LOGIN_URL = "/players/login";
    public static final String RESET_PASSWORD_URL = "/players/resetPassword";
    public static final String IDENTIFIER = "identifier";
    public static final String PLAYER_ID = "playerId"; 

}
