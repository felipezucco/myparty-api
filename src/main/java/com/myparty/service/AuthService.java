package com.myparty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myparty.dto.LoginDTO;
import com.myparty.dto.TokenDTO;
import com.myparty.exception.MismatchedPasswordException;
import com.myparty.model.Account;
import com.myparty.repository.AccountRepository;
import com.myparty.security.CachedTokens;
import com.myparty.security.JWTService;

import io.jsonwebtoken.Claims;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optAccount = accountRepository.findByUsername(username);
		if (optAccount.isPresent()) {
			Account account = optAccount.get(); 
			return User.builder()
					.username(account.getUsername())
					.password(account.getPassword())
					.roles(account.getRole().name())
					.build();
		} else throw new UsernameNotFoundException("Login não encontrado!");
	}
	
	public UserDetails login(LoginDTO loginDTO) throws Exception {
		try {
			UserDetails user = loadUserByUsername(loginDTO.getUsername());
			boolean match = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
			if (match) return user;
			else throw new MismatchedPasswordException();			
		} catch (UsernameNotFoundException e) {
			throw e;
		}
	}
	
	public TokenDTO getNewToken(UserDetails user) {
		String username = user.getUsername();
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setUsername(username);
		
		String token = jwtService.createToken(username);
		CachedTokens.add(token);

		tokenDTO.setToken(token);
		return tokenDTO;
	}
	
	public TokenDTO validateToken(String token) {
		Claims claims = jwtService.validateToken(token);
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(token);
		tokenDTO.setUsername(claims.getSubject());
		return tokenDTO;
	}		
}
