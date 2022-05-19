package com.myparty.controller.middleware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myparty.dto.EventDTO;
import com.myparty.model.Event;
import com.myparty.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventMiddleware extends RootMiddleware {

    @Autowired
    private EventService eventService;

    public List<EventDTO> getEvents() {
        return convert(eventService.getEvents());
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventService.saveEvent(convert(eventDTO));
        return convert(event);
    }

    public EventDTO getEventById(Long id) {
        return convert(eventService.getEventById(id));
    }
    
    public List<EventDTO> getEventsByOrganizationId(Long id) {
        return convert(eventService.getEventsByOrganizationId(id));
    }
    
}
