package com.myparty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.OrganizerDTO;
import com.myparty.controller.middleware.OrganizationMiddleware;
import com.myparty.dto.UserWithoutPasswordDTO;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/org")
public class OrganizationController {
    
    @Autowired
    private OrganizationMiddleware middleware;        
    
    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getOrganizations() {
        return ResponseEntity.ok(middleware.getOrganizationsDTO());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity.ok(middleware.getOrganizationById(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistOrganization(@RequestBody OrganizationDTO organizationDTO) {
        middleware.persistOrganization(organizationDTO);
    }

    @GetMapping("/organizer")
    public ResponseEntity<List<OrganizerDTO>> getOrganizers() {
        return ResponseEntity.ok(middleware.getOrganizers());
    }

    @PostMapping("/organizer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistOrganizer(@RequestBody OrganizerDTO organizerDTO) {
        middleware.persistOrganizer(organizerDTO);
    }

    @PostMapping("/organizer/user")
    public ResponseEntity<List<OrganizerDTO>> getOrganizerByUser(@RequestBody UserWithoutPasswordDTO userDTO) {
        return ResponseEntity.ok(middleware.getOrganizerByUser(userDTO));
    }
}
