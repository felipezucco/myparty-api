package com.myparty.middleware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myparty.dto.event.GetEvent;
import com.myparty.model.Event;
import com.myparty.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventMiddleware extends RootMiddleware {

    @Autowired
    private EventService eventService;

    public List<GetEvent> getEvents() {
        return convert(eventService.getEvents());
    }

    public GetEvent persistEvent(GetEvent getEvent) {
        Event event = eventService.persistEvent(convert(getEvent));
        return convert(event);
    }

    public GetEvent getEventById(Long id) {
        return convert(eventService.getEventById(id));
    }
    
    public List<GetEvent> getEventsByOrganizationId(Long id) {
        return convert(eventService.getEventsByOrganizationId(id));
    }
    
}
