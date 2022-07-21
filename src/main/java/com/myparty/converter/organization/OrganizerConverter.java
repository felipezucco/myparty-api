package com.myparty.converter.organization;

import com.myparty.converter.ConverterComponent;
import com.myparty.converter.RoleConverterOld;
import com.myparty.converter.user.UserWithoutPasswordConverterOld;
import com.myparty.dto.organization.*;
import com.myparty.enums.RoleEnum;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.organization.Organizer;
import com.myparty.service.OrganizationService;
import com.myparty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
@AllArgsConstructor
public class OrganizerConverter extends ConverterComponent implements DataConverterInterface<Organizer> {

    private UserService userService;
    private OrganizationService organizationService;

    @Override
    public <T> T convert(Organizer organizer, T destinationClass) {
        if (destinationClass == null) {
            destinationClass = getDefault(destinationClass, GetOrganizer.class);
        }

        if (destinationClass instanceof GetOrganizer) {
            GetOrganizer go = (GetOrganizer) destinationClass;
            go.setId(organizer.getId());
            go.setUser(transform(organizer.getUser()));
            go.setRole(organizer.getRole().getId());
        }
        if (destinationClass instanceof GetOrganizerWithOrganization) {
            GetOrganizerWithOrganization gowo = (GetOrganizerWithOrganization) destinationClass;
            gowo.setOrganization(transform(organizer.getOrganization(), GetOrganizationWithOrganizers.class));
        }
        return (T) destinationClass;
    }
    
    @Override
    public Organizer revert(Object o) {
        Organizer organizer = new Organizer();

        if (o instanceof PersistOrganizer) {
            PersistOrganizer po = (PersistOrganizer) o;
            organizer.setUser(userService.getUserById(po.getUserId()));
            organizer.setRole(RoleEnum.getRoleEnumById(po.getRole()));

            if (po.getOrganizationId() != null) {
                organizer.setOrganization(organizationService.getOrganizationById(po.getOrganizationId()));
            }
        }

        return organizer;
    }
    
}
