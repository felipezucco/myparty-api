package com.tendelfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tendelfc.dto.LoginDTO;
import com.tendelfc.dto.TokenDTO;
import com.tendelfc.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {			
	
	@Autowired
	private AuthService authService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		try {
			UserDetails user = authService.login(loginDTO);
			return ResponseEntity.ok(authService.getNewToken(user));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário e/ou senha inválidos!");
		}
	}
	
	@PostMapping("/recover")
	public ResponseEntity<?> recoverAuth(@RequestBody TokenDTO token) {
		TokenDTO tokenDTO = authService.validateToken(token.getToken());
		return ResponseEntity.status(HttpStatus.OK).body(tokenDTO);
	}
	
}
