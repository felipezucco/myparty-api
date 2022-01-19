package com.tendelfc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tendelfc.dto.AccountDTO;
import com.tendelfc.model.Account;
import com.tendelfc.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody AccountDTO userDTO) {
		accountService.checkUsernameEmailAccount(userDTO.getUsername(), userDTO.getEmail());
		accountService.saveUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok(accountService.getUsers());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		accountService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
