package com.myparty.service;

import com.myparty.model.Visual;
import com.myparty.repository.VisualRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VisualService {

    private VisualRepository visualRepository;

    public Visual persistVisual(Visual visual) {
        return visualRepository.save(visual);
    }

    public List<Visual> getVisualByEventId(Long eventId) {
        return visualRepository.findByEventId(eventId);
    }

    public void removeVisual(Long id) {
        visualRepository.deleteById(id);
    }

    public Visual getVisualById(Long id) {
        return visualRepository.getById(id);
    }

}
