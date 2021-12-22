package com.tendelfc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tendelfc.model.Organizer;
import com.tendelfc.repository.OrganizerRepository;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
	
	@Autowired
	private OrganizerRepository organizadorRepository;

	@GetMapping
	public List<Organizer> organizadores() {
		return organizadorRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Organizer novoOrganizador(@RequestBody Organizer organizador) {
		return organizadorRepository.save(organizador);
		
	}
	
}
