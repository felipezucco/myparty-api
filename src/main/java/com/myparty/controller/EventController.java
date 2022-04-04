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

import com.myparty.dto.EventDTO;
import com.myparty.service.EventService;


@RestController
@RequestMapping("/api/event")
public class EventController {

	@Autowired
	private EventService eventService;
		
	@GetMapping
	private ResponseEntity<List<EventDTO>> getEvents() {
		return ResponseEntity.ok(eventService.getEvents());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
		eventService.saveEvent(eventDTO);
		return ResponseEntity.ok(eventDTO);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<EventDTO> getEventById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(eventService.getEventById(id));
	}
		
}
