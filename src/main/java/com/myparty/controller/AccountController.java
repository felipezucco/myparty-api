package com.myparty.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.AccountDTO;
import com.myparty.service.AccountService;
import com.myparty.utils.Base64Decoder;


@RestController
@RequestMapping("/api/user")
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
	
	//Endpoint to search accounts
	@GetMapping("/s") 
	public ResponseEntity<List<String>> getUserByParam(@RequestParam String q, @RequestParam String v) {
		return ResponseEntity.ok(accountService.getUserEmailByParam(Base64Decoder.decode(v)));
	}
	
}
