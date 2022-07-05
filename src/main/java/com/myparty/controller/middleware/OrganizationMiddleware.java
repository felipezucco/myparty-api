/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.controller.middleware;

import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.organizer.OrganizerDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.model.UserProfile;
import com.myparty.model.organization.Organization;
import com.myparty.model.organization.Organizer;
import com.myparty.service.OrganizationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*Rule between controller and service layer used to converter data*/
@Service
public class OrganizationMiddleware extends RootMiddleware {

    @Autowired
    private OrganizationService organizationService;
    
    public List<OrganizationDTO> getOrganizationsDTO() {
        List<Organization> organizations = organizationService.getOrganizations();        
        return convert(organizations);
    }
    
    public OrganizationDTO getOrganizationById(Long id) {
        return convert(organizationService.getOrganizationById(id));
    }

    public OrganizationDTO persistOrganization(OrganizationDTO organizationDTO) {
        Organization org = convert(organizationDTO);
        organizationService.persistOrganization(org);
        return convert(org);
    }

    public List<OrganizerDTO> getOrganizers() {
        return convert(organizationService.getOrganizers());
    }

    public OrganizerDTO persistOrganizer(OrganizerDTO organizerDTO) {
        Organizer org = convert(organizerDTO);
        organizationService.persistOrganizer(org);
        return convert(org);
    }
    
    public List<OrganizerDTO> getOrganizerByUser(UserWithoutPasswordDTO userDTO) {
        UserProfile user = convert(userDTO);
        List<Organizer> organizerByUser = organizationService.getOrganizerByUser(user);
         List<OrganizerDTO> convert = convert(organizerByUser);
        return convert;
    }
    
}

