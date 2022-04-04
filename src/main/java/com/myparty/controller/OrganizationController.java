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
import com.myparty.service.OrganizationService;


@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;

	@GetMapping
	public ResponseEntity<List<OrganizationDTO>> getOrganizations() {
		return ResponseEntity.ok(organizationService.getOrganizations());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<OrganizationDTO> persistOrganization(@RequestBody OrganizationDTO organizationDTO) {
		organizationService.persistOrganization(organizationDTO);
		return ResponseEntity.ok(organizationDTO);
		
	}
	
	@GetMapping("/organizer")
	public ResponseEntity<List<OrganizerDTO>> getOrganizers() {
		return ResponseEntity.ok(organizationService.getOrganizers());
		
	}
	
	@PostMapping("/organizer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<OrganizerDTO> persistOrganizer(@RequestBody OrganizerDTO organizerDTO) {
		organizationService.persistOrganizer(organizerDTO);
		return ResponseEntity.ok(organizerDTO);
		
	}
	
	
	
}
