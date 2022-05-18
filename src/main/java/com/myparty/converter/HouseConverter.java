/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.house.HouseDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.House;
import com.myparty.model.Local;
import com.myparty.model.Zone;
import java.util.List;

/**
 *
 * @author Felipe Zucco
 */
public class HouseConverter implements DataConverter<House, HouseDTO>{

    private DataConverterImplement converter = new DataConverterImplement();
    
    @Override
    public HouseDTO convert(House entity) {        
        HouseDTO dto = new HouseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        
        Local local = entity.getLocal();
        dto.setLocal(converter.convert(local));
        
        List<Zone> zones = entity.getZones();
        dto.setZones(converter.convert(zones));
        return dto;
    }

    @Override
    public House revert(HouseDTO dto) {
        House house = new House();
        house.setId(dto.getId());
        house.setLocal(converter.convert(dto.getLocal()));
        house.setName(dto.getName());
        house.setZones(converter.convert(dto.getZones()));
        return house;
    }
    
}
