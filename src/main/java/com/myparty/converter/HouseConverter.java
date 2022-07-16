/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.house.GetHouse;
import com.myparty.dto.house.PersistHouse;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Local;
import com.myparty.model.house.House;
import com.myparty.model.house.Zone;
import com.myparty.service.LocalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Felipe Zucco
 */
@Component
@AllArgsConstructor
public class HouseConverter extends ConverterComponent implements DataConverterInterface<House> {

    private LocalService localService;

    @Override
    public <T> T convert(House entity, T destinationClass) {
        GetHouse dto = new GetHouse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        Local local = entity.getLocal();
        dto.setLocal(transform(local));

        List<Zone> zones = entity.getZones();
        dto.setZones(transform(zones));

        return (T) dto;
    }

    @Override
    public House revert(Object o) {
        House house = new House();

        if (o instanceof PersistHouse) {
            PersistHouse ph = (PersistHouse) o;
            house.setLocal(transform(ph.getLocal()));
            house.setName(ph.getName());
            house.setZones(transform(ph.getZones()));
            house.getZones().forEach(zone -> zone.setHouse(house));
        }
        if (o instanceof GetHouse) {
            GetHouse h = (GetHouse) o;
            house.setId(h.getId());
        }
        return house;
    }
}
