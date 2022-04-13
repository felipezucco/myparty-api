package com.myparty.controller;

import com.myparty.controller.middleware.EventMiddleware;
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

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventMiddleware middleware;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents() {
        return ResponseEntity.ok(middleware.getEvents());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistEvent(@RequestBody EventDTO eventDTO) {        
        middleware.createEvent(eventDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(middleware.getEventById(id));
    }

}
