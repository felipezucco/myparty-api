/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.local.GetLocal;
import com.myparty.dto.local.PersistLocal;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Local;
import com.myparty.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
@AllArgsConstructor
public class LocalConverter extends ConverterComponent implements DataConverterInterface<Local> {

    private OrganizationService organizationService;

    @Override
    public <T> T convert(Local entity, T destinationClass) {

        GetLocal dto = new GetLocal();
        dto.setAisle(entity.getAisle());
        dto.setBlock(entity.getBlock());
        dto.setCity(entity.getCity());
        dto.setCode(entity.getCode());
        dto.setComplement(entity.getComplement());
        dto.setCoordenateX(entity.getCoordenateX());
        dto.setCoordenateY(entity.getCoordenateY());
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setState(entity.getState());
        dto.setOrganization(transform(entity.getOrganization()));

        return (T) dto;
    }

    @Override
    public Local revert(Object o) {
        Local local = new Local();

        if (o instanceof PersistLocal) {
            PersistLocal pl = (PersistLocal) o;
            local.setAisle(pl.getAisle());
            local.setBlock(pl.getBlock());
            local.setCity(pl.getCity());
            local.setCode(pl.getCode());
            local.setComplement(pl.getComplement());
            local.setCoordenateX(pl.getCoordenateX());
            local.setCoordenateY(pl.getCoordenateY());
            local.setNumber(pl.getNumber());
            local.setState(pl.getState());
            local.setOrganization(organizationService.getOrganizationById(pl.getOrganizationId()));
        }

        if (o instanceof GetLocal) {
            GetLocal l = (GetLocal) o;
            local.setId(l.getId());
            local.setOrganization(transform(l.getOrganization()));
        }
        return local;
    }

    

}
