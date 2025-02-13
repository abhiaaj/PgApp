package com.sapple.tot.security;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 120 * 60 * 60; //5 days
	public static final String SIGNING_KEY = "fieldoo@sapple";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
}
