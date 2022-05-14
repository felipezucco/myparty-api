/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.organizer.OrganizerDTO;
import com.myparty.dto.organizer.OrganizerWithoutOrganizationDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.Organizer;

/**
 *
 * @author Felipe Zucco
 */
public class OrganizerWithoutOrganizationConverter implements DataConverter<Organizer, OrganizerWithoutOrganizationDTO>{
    
    @Override
    public OrganizerWithoutOrganizationDTO convert(Organizer organizer) {
        OrganizerWithoutOrganizationDTO dto = new OrganizerWithoutOrganizationDTO();
        dto.setId(organizer.getId());
        dto.setRole(organizer.getRole().getId());
        
        UserWithoutPasswordConverter converter = new UserWithoutPasswordConverter();
        dto.setUser(converter.convert(organizer.getUser()));
        return dto;
    }

    @Override
    public Organizer revert(OrganizerWithoutOrganizationDTO dto) {
        Organizer entity = new Organizer();
        entity.setId(dto.getId());
        
        RoleConverter roleConverter = new RoleConverter();
        entity.setRole(roleConverter.revert(dto.getRole()));
        
        UserWithoutPasswordConverter userConverter = new UserWithoutPasswordConverter();
        entity.setUser(userConverter.revert(dto.getUser()));
        return entity;
    }
    
    
}
