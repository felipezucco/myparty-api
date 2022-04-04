package com.myparty.controller;

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

import com.myparty.dto.AccountDTO;
import com.myparty.service.AccountService;


@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<AccountDTO> persistUser(@RequestBody AccountDTO userDTO) {
		accountService.checkUsernameEmailAccount(userDTO.getUsername(), userDTO.getEmail());
		accountService.persistUser(userDTO);
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
