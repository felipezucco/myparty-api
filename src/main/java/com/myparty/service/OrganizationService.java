package com.myparty.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.OrganizerDTO;
import com.myparty.model.Organization;
import com.myparty.model.Organizer;
import com.myparty.repository.OrganizationRepository;
import com.myparty.repository.OrganizerRepository;


@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private OrganizerRepository organizerRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public OrganizationDTO persistOrganization(OrganizationDTO organizationDTO) {
		Organization organization = mapper.map(organizationDTO, Organization.class);
		organizationRepository.save(organization);
		organizationDTO.setId(organization.getId());
		return organizationDTO;
	}
	
	public OrganizerDTO persistOrganizer(OrganizerDTO organizerDTO) {
		Organizer organizer = mapper.map(organizerDTO, Organizer.class);
		organizerRepository.save(organizer);
		organizerDTO.setId(organizer.getId());
		return organizerDTO;
	}
	
	public List<OrganizationDTO> getOrganizations() {
		List<Organization> organizations = organizationRepository.findAll();
		return organizations.stream().map(org -> mapper.map(org, OrganizationDTO.class)).collect(Collectors.toList());
	}
	
	public OrganizationDTO getOrganizationById(Long id) {
		Optional<Organization> organization = organizationRepository.findById(id);		
		return organization.map(l -> mapper.map(l, OrganizationDTO.class)).orElseGet(null); 
	}
	
	public List<OrganizerDTO> getOrganizers() {
		List<Organizer> organizer = organizerRepository.findAll();		
		return organizer.stream().map(o -> mapper.map(o, OrganizerDTO.class)).collect(Collectors.toList()); 
	}
	
}
