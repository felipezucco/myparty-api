package com.myparty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.exception.OrganizationException;
import com.myparty.model.UserProfile;
import com.myparty.model.organization.Organization;
import com.myparty.model.organization.Organizer;
import com.myparty.repository.OrganizationRepository;
import com.myparty.repository.OrganizerRepository;

@Service
/*Class that communicate with repository*/
public class OrganizationService {

    @Autowired
    private OrganizerRepository organizerRepository;
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    public void persistOrganization(Organization organization) {
        //organization.getOrganizers().parallelStream().forEach(organizer -> organizer.setOrganization(organization)); //this is necessary to save organization_id 
        organizationRepository.save(organization);
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

    public List<Organizer> getOrganizers() {       
        return organizerRepository.findAll();
    }

    public List<Organizer> getOrganizerByUser(UserProfile user) {
        return organizerRepository.findByUser(user);
    }
    
}
