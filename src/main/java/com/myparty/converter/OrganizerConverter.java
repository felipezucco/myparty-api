package com.myparty.converter;

import com.myparty.dto.organizer.OrganizerDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.Organizer;

/**
 *
 * @author Felipe Zucco
 */
public class OrganizerConverter implements DataConverter<Organizer, OrganizerDTO>{
    
    @Override
    public OrganizerDTO convert(Organizer organizer) {
        OrganizerDTO dto = new OrganizerDTO();
        dto.setId(organizer.getId());
        dto.setRole(organizer.getRole().getId());
        
        UserWithoutPasswordConverter converter = new UserWithoutPasswordConverter();
        dto.setUser(converter.convert(organizer.getUser()));
        
        OrganizationConverter organizationConverter = new OrganizationConverter();
        dto.setOrganization(organizationConverter.convert(organizer.getOrganization()));
        return dto;
    }
    
    @Override
    public Organizer revert(OrganizerDTO dto) {
        Organizer organizer = new Organizer();
        organizer.setId(dto.getId());
        
        RoleConverter roleConverter = new RoleConverter();
        organizer.setRole(roleConverter.revert(dto.getRole()));
        
        UserWithoutPasswordConverter userConverter = new UserWithoutPasswordConverter();
        organizer.setUser(userConverter.revert(dto.getUser()));
        
        OrganizationConverter organizationConverter = new OrganizationConverter();
        organizer.setOrganization(organizationConverter.revert(dto.getOrganization()));
        return organizer;
    }
    
}
