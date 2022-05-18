/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.LocalDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.Local;

/**
 *
 * @author Felipe Zucco
 */
public class LocalConverter implements DataConverter<Local, LocalDTO>{
    
    private DataConverterImplement converter = new DataConverterImplement();
    
    @Override
    public LocalDTO convert(Local entity) {
        LocalDTO dto = new LocalDTO();
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
        dto.setOrganization(converter.convert(entity.getOrganization()));
        return dto;
    }

    @Override
    public Local revert(LocalDTO dto) {
        Local local = new Local();
        local.setAisle(dto.getAisle());
        local.setBlock(dto.getBlock());
        local.setCity(dto.getCity());
        local.setCode(dto.getCode());
        local.setComplement(dto.getComplement());
        local.setCoordenateX(dto.getCoordenateX());
        local.setCoordenateY(dto.getCoordenateY());
        local.setId(dto.getId());
        local.setNumber(dto.getNumber());
        local.setState(dto.getState());
        local.setOrganization(converter.convert(dto.getOrganization()));
        return local;
    }

    

}
