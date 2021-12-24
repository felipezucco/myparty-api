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

import com.tendelfc.model.Event;
import com.tendelfc.model.Local;
import com.tendelfc.repository.EventRepository;
import com.tendelfc.repository.LocalRepository;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private LocalRepository localRepository;
	
	@GetMapping
	private List<Event> getEvents() {
		return eventRepository.findAll();
	}
	
	@PostMapping(value = "/newEvent")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Event createEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}
	
	@PostMapping(value = "/newLocal")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Local createLocal(@RequestBody Local local) {
		return localRepository.save(local);
	}
	
}
