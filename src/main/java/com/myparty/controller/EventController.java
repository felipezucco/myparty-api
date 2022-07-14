package com.myparty.controller;

import com.myparty.dto.event.PersistEvent;

import java.util.List;

import com.myparty.service.EventService;
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

import com.myparty.dto.event.GetEvent;

@RestController
@RequestMapping("/api/event")
public class EventController extends ControllerComponent {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<List<GetEvent>> getEvents() {
        return ResponseEntity.ok(_8(service.getEvents()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistEvent(@RequestBody PersistEvent persistEvent) {
        service.persistEvent(_8(persistEvent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEvent> getEventById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(service.getEventById(id)));
    }
    
    @GetMapping("/org/{id}")
    public ResponseEntity<List<GetEvent>> getEventsByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(service.getEventsByOrganizationId(id)));
    }

}
