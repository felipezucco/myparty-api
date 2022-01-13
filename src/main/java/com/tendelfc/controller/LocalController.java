package com.tendelfc.controller;

import java.net.URI;
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

import com.tendelfc.dto.LocalDTO;
import com.tendelfc.service.LocalService;

@RestController
@RequestMapping("/local")
public class LocalController {

	@Autowired
	private LocalService localService;
	
	@GetMapping
	private ResponseEntity<?> getLocals() throws Exception {	
		List<LocalDTO> localsDTO = localService.getLocals(); 
		return ResponseEntity.ok(localsDTO);			
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<?> createLocal(@RequestBody LocalDTO local) throws Exception {
		localService.saveLocal(local);
		return ResponseEntity.created(URI.create("/local/"+local.getId())).build();			
	}
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<?> getLocal(@PathVariable("id") Long id) {
		return ResponseEntity.ok(localService.getLocalById(id));
	}
		
	
}
