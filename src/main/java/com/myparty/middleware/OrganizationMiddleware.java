/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.middleware;

import com.myparty.dto.organization.GetOrganization;
import com.myparty.dto.organization.GetOrganizerWithOrganization;
import com.myparty.dto.user.GetUserWithPassword;
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
    
    public List<GetOrganization> getOrganizationsDTO() {
        List<Organization> organizations = organizationService.getOrganizations();        
        return convert(organizations);
    }
    
    public GetOrganization getOrganizationById(Long id) {
        return convert(organizationService.getOrganizationById(id));
    }

    public GetOrganization persistOrganization(GetOrganization getOrganization) {
        Organization org = convert(getOrganization);
        organizationService.persistOrganization(org);
        return convert(org);
    }

    public List<GetOrganizerWithOrganization> getOrganizers() {
        return convert(organizationService.getOrganizers());
    }

    public GetOrganizerWithOrganization persistOrganizer(GetOrganizerWithOrganization organizerWithOrganizationDTO) {
        Organizer org = convert(organizerWithOrganizationDTO);
        organizationService.persistOrganizer(org);
        return convert(org);
    }
    
    public List<GetOrganizerWithOrganization> getOrganizerByUser(GetUserWithPassword userDTO) {
        UserProfile user = convert(userDTO);
        List<Organizer> organizerByUser = organizationService.getOrganizerByUser(user);
         List<GetOrganizerWithOrganization> convert = convert(organizerByUser);
        return convert;
    }
    
}

