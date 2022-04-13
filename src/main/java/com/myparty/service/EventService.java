package com.myparty.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.model.Event;
import com.myparty.repository.EventRepository;

@Service
public class EventService extends RootService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        eventRepository.save(event);
        return event;
    }
    
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

}
