package com.tendelfc.controller;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		try {
			UserDetails user = authService.login(loginDTO);
			return ResponseEntity.ok(authService.getToken(user));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário e/ou senha inválidos!");
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		return ResponseEntity.ok("Logout realizado com sucesso!");
	}
	
	@PostMapping("/recover")
	public ResponseEntity<?> recoverAuth(@RequestBody String token) {
		TokenDTO tokenDTO = authService.validateToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(tokenDTO);
	}
	
}
