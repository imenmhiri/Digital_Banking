package com.fss.Digital.Banking.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fss.Digital.Banking.config.JWTUtil;
import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.exceptions.AuthorizationProblemException;
import com.fss.Digital.Banking.services.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Controlller {
	@Autowired
	private ClientService ClientServiceImpl;

	@GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, AuthorizationProblemException {
		String authorizationToken = request.getHeader("Authorization");
		if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.PREFIX))
			try {
				String refreshToken = authorizationToken.substring(JWTUtil.PREFIX.length());
				Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
				String username = decodedJWT.getSubject();
				Client user =ClientServiceImpl.chargerUtilisateurParUsername(username); 
				String jwtAccessToken = JWT.create().withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
						.withIssuer(request.getRequestURL().toString()).withClaim("roles",
								user.getRole().stream().map(r -> r.getNomDuRole()).collect(Collectors.toList())).sign(algorithm); 
				Map <String,String> idToken= new HashMap<>(); 	
	            idToken.put("acess-token", jwtAccessToken);
	            idToken.put("refresh-token", refreshToken);
	            response.setContentType("application/json");
			} catch (Exception e) {
	               response.setHeader("error message", e.getMessage());
	               response.sendError(HttpServletResponse.SC_FORBIDDEN);	
				}
		else {
			throw new AuthorizationProblemException("probléme d\'Authorization"); 
		}
				

	}
}
