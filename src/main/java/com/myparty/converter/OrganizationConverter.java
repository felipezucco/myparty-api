/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.organizer.OrganizerWithoutOrganizationDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.Organization;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Felipe Zucco
 */
public class OrganizationConverter implements DataConverter<Organization, OrganizationDTO>{

    @Override
    public OrganizationDTO convert(Organization organization) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(organization.getId());
        dto.setName(organization.getName());
        
        OrganizerWithoutOrganizationConverter organizationConverter = new OrganizerWithoutOrganizationConverter();
        List<OrganizerWithoutOrganizationDTO> organizerDTOs = organization.getOrganizers().stream().map(org -> organizationConverter.convert(org)).collect(Collectors.toList());
        dto.setOrganizers(organizerDTOs);
        return dto;
    }    
    
    @Override
    public Organization revert(OrganizationDTO dto) {
        Organization organization = new Organization();
        organization.setId(dto.getId());
        organization.setName(dto.getName());
        //organization.setOrganizers(organizers);
        
        OrganizerWithoutOrganizationConverter organizationConverter = new OrganizerWithoutOrganizationConverter();
        List<OrganizerWithoutOrganizationDTO> organizerDTOs = organization.getOrganizers().stream().map(org -> organizationConverter.convert(org)).collect(Collectors.toList());
        dto.setOrganizers(organizerDTOs);
        return organization;
    }   
    
}
