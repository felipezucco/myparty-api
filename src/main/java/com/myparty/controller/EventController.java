package com.myparty.controller;

import com.myparty.dto.event.PersistEvent;

import java.util.List;

import com.myparty.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myparty.dto.event.GetEvent;

@RestController
@RequestMapping("/api/event")
public class EventController extends ControllerComponent {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<GetEvent>> getEvents() {
        return ResponseEntity.ok(_8(eventService.getEvents()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistEvent(@RequestBody PersistEvent persistEvent) {
        eventService.persistEvent(_8(persistEvent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEvent> getEventById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(eventService.getEventById(id)));
    }
    
    @GetMapping("/org/{id}")
    public ResponseEntity<List<GetEvent>> getEventsByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(eventService.getEventsByOrganizationId(id)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeEvent(@PathVariable("id") Long id) {
        eventService.removeEvent(id);
    }

}
