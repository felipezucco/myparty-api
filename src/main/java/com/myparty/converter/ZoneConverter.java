/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.ZoneDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.house.Zone;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class ZoneConverter implements DataConverter<Zone, ZoneDTO>{

    @Override
    public ZoneDTO convert(Zone entity) {
        ZoneDTO dto = new ZoneDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSize(entity.getSize());
        
        return dto;
    }

    @Override
    public Zone revert(ZoneDTO dto) {
        Zone zone = new Zone();
        zone.setId(dto.getId());
        zone.setName(dto.getName());
        zone.setSize(dto.getSize());
        return zone;
    }
    
}
