package com.myparty.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.dto.EventDTO;
import com.myparty.model.Event;
import com.myparty.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper mapper;

    public EventDTO toDTO(Event event) {
        return  mapper.map(event, EventDTO.class);
    }
    
    public List<EventDTO> getEvents() {
        return eventRepository.findAll().stream().map(e -> mapper.map(e, EventDTO.class)).collect(Collectors.toList());
    }

    public EventDTO saveEvent(EventDTO eventDTO) {
        Event event = mapper.map(eventDTO, Event.class);
        eventRepository.save(event);
        eventDTO.setId(event.getId());
        return eventDTO;
    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
    
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

}
