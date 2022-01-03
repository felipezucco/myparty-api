package com.tendelfc.controller;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.tendelfc.model.Event;
import com.tendelfc.model.Local;
import com.tendelfc.repository.EventRepository;
import com.tendelfc.repository.LocalRepository;
import com.tendelfc.service.LocalService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private LocalService localService;
	
	@GetMapping
	private List<Event> getEvents() {
		return eventRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private Event createEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}
	
//	@GetMapping(value = "/local")
//	private ResponseEntity<?> getLocals() throws Exception {	
//		List<Local> locals = localRepository.findAll();
//		return ResponseEntity.ok(locals);			
//	}
	
	@PostMapping(value = "/local")
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<?> createLocal(@RequestBody LocalDTO local) throws Exception {
		localService.saveLocal(local);
		return ResponseEntity.created(URI.create("/local/"+local.getId())).build();			
	}
//	
//	@GetMapping(value = "/local/{id}")
//	private ResponseEntity<?> getLocal(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(localRepository.findById(id));
//	}
	
}
