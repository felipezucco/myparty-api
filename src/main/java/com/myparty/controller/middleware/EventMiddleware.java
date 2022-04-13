package com.myparty.controller.middleware;

import com.myparty.controller.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myparty.dto.EventDTO;
import com.myparty.model.Event;
import com.myparty.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventMiddleware extends RootController {

    @Autowired
    private EventService eventService;

    public List<EventDTO> getEvents() {
        return data.convert(eventService.getEvents());
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventService.saveEvent(data.convert(eventDTO));
        return data.convert(event);
    }

    public EventDTO getEventById(Long id) {
        return data.convert(eventService.getEventById(id));
    }

}
