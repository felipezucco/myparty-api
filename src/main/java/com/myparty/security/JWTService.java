package com.myparty.security;

import com.myparty.controller.middleware.UserMiddleware;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.enums.UserSearchEnum;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.claims;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class JWTService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signature}")
    private String signature;
    
    @Autowired
    private UserMiddleware userMiddleware;

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

    public Claims validateToken(String token) {
        try {
            CachedTokens.validyToken(token);
            return getClaims(token);
        } catch (Exception e) {
            CachedTokens.remove(token);
            throw e;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public static String checkToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer")) {
            return authorization.trim().split(" ")[1];
        } else {
            return null;
        }
    }

}
