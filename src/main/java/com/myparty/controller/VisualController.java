package com.myparty.controller;

import com.myparty.dto.visual.GetVisual;
import com.myparty.dto.visual.PersistVisual;
import com.myparty.service.VisualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visual")
public class VisualController extends ControllerComponent {

    @Autowired
    private VisualService visualService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void persistVisual(@RequestBody PersistVisual persistVisual) {
        visualService.persistVisual(_8(persistVisual));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<GetVisual>> getVisualByEventId(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(_8(visualService.getVisualByEventId(eventId), GetVisual.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeVisual(@PathVariable("id") Long id) {
        visualService.removeVisual(id);
    }

}
