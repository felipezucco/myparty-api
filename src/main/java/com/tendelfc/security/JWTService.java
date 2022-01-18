package com.tendelfc.security;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tendelfc.model.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {

	@Value("${security.jwt.expiration}")
	private String expiration;
		
	@Value("${security.jwt.signature}")
	private String signature;
	
	public String createToken(String username) {
		Long exp = Long.parseLong(expiration);
		
		Calendar expCalendar = Calendar.getInstance();
		expCalendar.add(Calendar.MINUTE, exp.intValue());
		Date expDate = expCalendar.getTime();
		
		JwtBuilder jwtBuilder = Jwts.builder()
				.setSubject(username)
				.setExpiration(expDate)
				.signWith(SignatureAlgorithm.HS512, signature);
		
		return jwtBuilder.compact();
	}
	
	public Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(signature).parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token) {
		try {
			getClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUsernameFromToken(String token) {
		return getClaims(token).getSubject();
	}
	
	public String checkToken(String authorization) {
		if (authorization != null && authorization.startsWith("Bearer")) {
			return authorization.trim().split(" ")[1];
		} else return null;
		//else throw new RuntimeException("Token inválido!");
	}
	
}
