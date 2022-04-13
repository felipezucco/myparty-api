/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.controller.middleware;

import com.myparty.controller.RootController;
import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.OrganizerDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.model.Organization;
import com.myparty.model.Organizer;
import com.myparty.model.UserProfile;
import com.myparty.service.OrganizationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
/*Rule between controller and service layer used to converter data*/
public class OrganizationMiddleware extends RootController {

    @Autowired
    private OrganizationService organizationService;
    
    public List<OrganizationDTO> getOrganizationsDTO() {
        List<Organization> organizations = organizationService.getOrganizations();        
        return data.convert(organizations);
    }
    
    public OrganizationDTO getOrganizationById(Long id) {
        return data.convert(organizationService.getOrganizationById(id));
    }

    public OrganizationDTO persistOrganization(OrganizationDTO organizationDTO) {
        Organization org = data.convert(organizationDTO);
        organizationService.persistOrganization(org);
        return data.convert(org);
    }

    public List<OrganizerDTO> getOrganizers() {
        return data.convert(organizationService.getOrganizers());
    }

    public OrganizerDTO persistOrganizer(OrganizerDTO organizerDTO) {
        Organizer org = data.convert(organizerDTO);
        organizationService.persistOrganizer(org);
        return data.convert(org);
    }
    
    public List<OrganizerDTO> getOrganizerByUser(UserWithoutPasswordDTO userDTO) {
        UserProfile user = data.convert(userDTO);
        return data.convert(organizationService.getOrganizerByUser(user));
    }
    
}

