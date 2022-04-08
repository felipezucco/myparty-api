package com.myparty.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.OrganizerDTO;
import com.myparty.exception.OrganizationException;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Organization;
import com.myparty.model.Organizer;
import com.myparty.repository.OrganizationRepository;
import com.myparty.repository.OrganizerRepository;

@Service
public class OrganizationService extends RootService<Organization, OrganizationDTO> {

    @Autowired
    private OrganizerRepository organizerRepository;
    
    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationDTO persistOrganization(OrganizationDTO organizationDTO) {
        Organization organization = mapper.map(organizationDTO, Organization.class);        
        organizationRepository.save(organization);
        organizationDTO.setId(organization.getId());
        return organizationDTO;
    }   

    public void persistOrganization(DataConverterInterface organization) {
        organization.
        dto(organization);
        organizationRepository.save(organization);
    }

    public OrganizerDTO persistOrganizer(OrganizerDTO organizerDTO) {
        Organizer organizer = mapper.map(organizerDTO, Organizer.class);
        persistOrganizer(organizer);
        organizerDTO.setId(organizer.getId());
        return organizerDTO;
    }

    public void persistOrganizer(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id).orElseThrow(OrganizationException.OrganizationNotFoundException::new);
    }

    public List<OrganizerDTO> getOrganizers() {
        List<Organizer> organizer = organizerRepository.findAll();
        return organizer.stream().map(o -> mapper.map(o, OrganizerDTO.class)).collect(Collectors.toList());
    }

}
