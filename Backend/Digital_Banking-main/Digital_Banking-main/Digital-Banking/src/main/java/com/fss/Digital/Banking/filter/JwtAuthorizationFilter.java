package com.fss.Digital.Banking.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fss.Digital.Banking.config.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/refreshToken)")) {
			filterChain.doFilter(request, response);

		} else {
			String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
			if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.PREFIX))
				try {
					/*
					 * Au niveau de la requete nous recevons le mot Bearer pour indiquer c est un
					 * jwt token apres la valeur du token pour recuperer juste la valeur du token on
					 * doit faire l'instruction ci dessous
					 */
					String jwt = authorizationToken.substring(JWTUtil.PREFIX.length());
					Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					// pour verifiée si le token est bonne a expirée ou pas
					DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
					String username = decodedJWT.getSubject();
					String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
					Collection<GrantedAuthority> authorities = new ArrayList<>();
					for (String r : roles) {
						authorities.add(new SimpleGrantedAuthority(r));
					}
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, authorities);
					// pour voir ci l utilsateur a le droit ou non
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				} catch (Exception e) {
					response.setHeader("error message", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				}
			else
				filterChain.doFilter(request, response);

		}
	}
}
