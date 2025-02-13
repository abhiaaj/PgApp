package com.sapple.tot.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*
 * Entry point for JWT authentication for every incoming request.
*/

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	private static final long serialVersionUID = -6760438813059263327L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		logger.info("inside JwtAuthenticationEntryPoint class");

		logger.info("Token expired or invalid");

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
