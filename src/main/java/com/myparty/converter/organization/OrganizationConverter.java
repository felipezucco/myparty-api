/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter.organization;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.organization.*;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class OrganizationConverter extends ConverterComponent implements DataConverterInterface<Organization> {

    @Override
    public <T> T convert(Organization organization, T destinationClass) {
        if (destinationClass == null) {
            destinationClass = (T) new GetOrganizationWithOrganizers();
        }

        if (destinationClass instanceof GetOrganization) {
            GetOrganization go = (GetOrganization) destinationClass;
            go.setId(organization.getId());
            go.setName(organization.getName());
        }
        if (destinationClass instanceof GetOrganizationWithOrganizers) {
            GetOrganizationWithOrganizers gowo = (GetOrganizationWithOrganizers) destinationClass;
            gowo.setOrganizers(transform(organization.getOrganizers(), GetOrganizer.class));
        }

        return (T) destinationClass;
    }    
    
    @Override
    public Organization revert(Object o) {
        Organization organization = new Organization();

        if (o instanceof PersistOrganization) {
            PersistOrganization po = (PersistOrganization) o;
            organization.setName(po.getName());
            organization.setFavorite(po.getFavorite());
            organization.setOrganizers(transform(po.getOrganizers()));
        }

        return organization;
    }

}
