package com.myparty.controller;

import java.util.List;

import com.myparty.dto.organization.GetOrganizationWithOrganizers;
import com.myparty.model.organization.Organizer;
import com.myparty.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.organization.GetOrganization;
import com.myparty.dto.organization.GetOrganizerWithOrganization;
import org.springframework.web.bind.annotation.PathVariable;

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
    public void persistOrganization(@RequestBody GetOrganizationWithOrganizers getOrganization) {
        organizationService.persistOrganization(_8(getOrganization, GetOrganizationWithOrganizers.class));
    }

    @GetMapping("/organizer")
    public ResponseEntity<List<GetOrganizerWithOrganization>> getOrganizers() {
        return ResponseEntity.ok(_8(organizationService.getOrganizers(), GetOrganizerWithOrganization.class));
    }

    @PostMapping("/organizer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistOrganizer(@RequestBody GetOrganizerWithOrganization organizerWithOrganizationDTO) {
        organizationService.persistOrganizer(_8(organizerWithOrganizationDTO, GetOrganizerWithOrganization.class));
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<List<GetOrganizerWithOrganization>> getOrganizerByUser(@PathVariable("id") Long userId) {
        List<Organizer> organizers = organizationService.getOrganizerByUserId(userId);
        return ResponseEntity.ok(_8(organizers, GetOrganizerWithOrganization.class));
    }
}
