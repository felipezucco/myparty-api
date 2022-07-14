package com.myparty.controller;

import com.myparty.dto.production.PersistProduction;
import com.myparty.dto.production.GetProduction;
import com.myparty.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production")
public class ProductionController extends ControllerComponent {

    @Autowired
    private ProductionService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void persistProduction(@RequestBody PersistProduction persistProduction) {
        service.persistProduction(_8(persistProduction));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<GetProduction>> getProductionByEventId(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(_8(service.getProductionByEventId(eventId)));
    }

}
