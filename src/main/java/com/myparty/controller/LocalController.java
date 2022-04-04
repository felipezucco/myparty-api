package com.myparty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.LocalDTO;
import com.myparty.service.LocalService;

@RestController
@RequestMapping("/api/local")
public class LocalController {

	@Autowired
	private LocalService localService;
	
	@GetMapping
	private ResponseEntity<List<LocalDTO>> getLocals() throws Exception {	
		List<LocalDTO> localsDTO = localService.getLocals(); 
		return ResponseEntity.ok(localsDTO);			
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<LocalDTO> createLocal(@RequestBody LocalDTO local) throws Exception {
		localService.saveLocal(local);
		return ResponseEntity.status(HttpStatus.CREATED).body(local);			
	}
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<LocalDTO> getLocal(@PathVariable("id") Long id) {
		return ResponseEntity.ok(localService.getLocalById(id));
	}		
	
}
