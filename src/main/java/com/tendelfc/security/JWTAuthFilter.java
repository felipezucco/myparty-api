package com.tendelfc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tendelfc.controller.handlers.RestAuthenticationEntryPoint;
import com.tendelfc.exception.ExpiratedTokenException;
import com.tendelfc.service.AuthService;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthFilter extends OncePerRequestFilter {

	private JWTService jwtService;
	private AuthService authService;

	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	public JWTAuthFilter(JWTService jwtService, AuthService authService) {
		super();
		this.jwtService = jwtService;
		this.authService = authService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");
		String token = jwtService.checkToken(authorization);

		boolean validToken = false;
		if (token != null) {
			try {
				validToken = !jwtService.validateToken(token).isEmpty();
			} catch (ExpiredJwtException e) {
				SecurityContextHolder.clearContext();
				authenticationEntryPoint.commence(request, response, new ExpiratedTokenException("Token inválido", e));
				return;
			}

			if (validToken) {
				String username = jwtService.getUsernameFromToken(token);
				UserDetails user = authService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);
	}
}
