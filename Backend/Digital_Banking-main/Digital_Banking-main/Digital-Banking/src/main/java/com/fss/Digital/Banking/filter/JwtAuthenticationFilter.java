package com.fss.Digital.Banking.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fss.Digital.Banking.config.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private DaoAuthenticationProvider authenticationManager;

	public JwtAuthenticationFilter(DaoAuthenticationProvider daoAuthenticationProvider) {
		this.authenticationManager = daoAuthenticationProvider;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authentificationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		return authenticationManager.authenticate(authentificationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authresult) {
		try {
			// permet de retourner l'utilisateur authentifiée
			User user = (User) authresult.getPrincipal();
			Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
			String jwtAccessToken = JWT.create().withSubject(user.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
					.withIssuer(request.getRequestURL().toString())
					.withClaim("roles", user.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()))
					.sign(algorithm);
			response.setHeader("Authorization", jwtAccessToken);
			String jwtRefreshToken = JWT.create().withSubject(user.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.REFRESH_ACCESS_TOKEN))
					.withIssuer(request.getRequestURL().toString()).sign(algorithm);
			response.setHeader("Authorization", jwtAccessToken);
			Map<String, String> idToken = new HashMap<>();
			idToken.put("acess-token", jwtAccessToken);
			idToken.put("refresh-token", jwtRefreshToken);
			response.setContentType("application/json");
			new ObjectMapper().writeValue(response.getOutputStream(), idToken);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}