package com.myparty.controller;

import java.util.ArrayList;
import java.util.List;

import com.myparty.dto.organization.*;
import com.myparty.model.organization.Organizer;
import com.myparty.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController extends ControllerComponent {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<GetOrganizationWithOrganizers>> getOrganizations() {
        return ResponseEntity.ok(_8(organizationService.getOrganizations(), GetOrganizationWithOrganizers.class));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizationWithOrganizers> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity.ok(_8(organizationService.getOrganizationById(id), GetOrganizationWithOrganizers.class));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistOrganization(@RequestBody PersistOrganization persistOrganization) {
        organizationService.persistOrganization(_8(persistOrganization));
    }

    @GetMapping("/organizer")
    public ResponseEntity<List<GetOrganizerWithOrganization>> getOrganizers() {
        return ResponseEntity.ok(_8(organizationService.getOrganizers(), GetOrganizerWithOrganization.class));
    }

    @PostMapping("/organizer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistOrganizer(@RequestBody PersistOrganizer persistOrganizer) {
        organizationService.persistOrganizer(_8(persistOrganizer));
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<List<GetOrganizerWithOrganization>> getOrganizerByUser(@PathVariable("id") Long userId) {
        List<Organizer> organizers = organizationService.getOrganizerByUserId(userId);
        return ResponseEntity.ok(_8(organizers, GetOrganizerWithOrganization.class));
    }

}
