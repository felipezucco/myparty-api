package com.tendelfc.controller;

import java.net.URI;

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

import com.tendelfc.dto.EventDTO;
import com.tendelfc.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;
		
	@GetMapping
	private ResponseEntity<?> getEvents() {
		return ResponseEntity.ok(eventService.getEvents());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
		eventService.saveEvent(eventDTO);
		URI uri = URI.create("/event/"+eventDTO.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/event/{id}")
	private ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(eventService.getEventById(id));
	}
		
}
