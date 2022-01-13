package com.tendelfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tendelfc.dto.AccountDTO;
import com.tendelfc.service.AccountService;

@RestController
@RequestMapping("/user")
public class AccountController {

	@Autowired
	private AccountService userService;
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody AccountDTO userDTO) {
		userService.saveUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}
	
}
