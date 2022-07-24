package com.myparty.service;

import com.myparty.converter.DataConverterEngine;
import com.myparty.dto.user.GetUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myparty.dto.LoginDTO;
import com.myparty.dto.TokenDTO;
import com.myparty.dto.user.GetUserWithPassword;
import com.myparty.enums.RoleEnum;
import com.myparty.exception.MismatchedPasswordException;
import com.myparty.model.UserProfile;
import com.myparty.security.CachedTokens;
import com.myparty.security.JWTService;

import io.jsonwebtoken.Claims;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {


    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private JWTService jwtService;
    private DataConverterEngine converter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile account = userService.getUserByUsername(username);
        if (account != null) {
            return User.builder()
                    .username(account.getUsername())
                    .password(account.getPassword())
                    .roles(RoleEnum.USER.name())
                    .build();
        }
		throw new UsernameNotFoundException("Login não encontrado!");
    }

    public UserDetails login(LoginDTO loginDTO) throws Exception {
        try {
            UserDetails user = loadUserByUsername(loginDTO.getUsername());
            boolean match = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
            if (match) {
                return user;
            }
			throw new MismatchedPasswordException();
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    public TokenDTO getNewToken(UserDetails user) {
    	String username = user.getUsername();
        TokenDTO tokenDTO = new TokenDTO();

        GetUser userStartsWith = converter.start(userService.getUserByUsername(username));
        tokenDTO.setUser(userStartsWith);

        String token = jwtService.createToken(username);
        CachedTokens.add(token);

        tokenDTO.setToken(token);
        return tokenDTO;
    }

    public TokenDTO validateToken(String token) {
        Claims claims = jwtService.validateToken(token);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setUser(converter.start(userService.getUserByUsername(claims.getSubject())));
        return tokenDTO;
    }
}
