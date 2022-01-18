package com.tendelfc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tendelfc.service.AccountService;
import com.tendelfc.service.AuthService;

public class JWTAuthFilter extends OncePerRequestFilter {

	private JWTService jwtService;
	private AuthService authService;
	
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
		if (jwtService.validateToken(token)) {
			String username = jwtService.getUsernameFromToken(token);
			UserDetails user = authService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, 
					null, user.getAuthorities());
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(request, response);
		
		
		
		
	}

	
	
}
