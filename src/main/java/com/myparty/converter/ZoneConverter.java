/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.house.GetZone;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.house.Zone;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class ZoneConverter extends ConverterComponent implements DataConverterInterface<Zone> {

    @Override
    public <T> T convert(Zone entity, T destinationClass) {
        GetZone dto = new GetZone();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSize(entity.getSize());

        return (T) dto;
    }

    @Override
    public Zone revert(Object o) {
        Zone zone = new Zone();

        if (o instanceof GetZone) {
            GetZone z = (GetZone) o;
            zone.setId(z.getId());
            zone.setName(z.getName());
            zone.setSize(z.getSize());
        }
        return zone;
    }
}
