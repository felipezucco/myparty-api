/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.event.GetEvent;
import com.myparty.dto.event.PersistEvent;
import com.myparty.enums.DateFormatEnum;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Event;
import com.myparty.service.HouseService;
import com.myparty.utils.DateFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@AllArgsConstructor
@Component
public class EventConverter extends ConverterComponent implements DataConverterInterface<Event> {

    private HouseService houseService;

    @Override
    public <T> T convert(Event entity, T destinationClass) {
        GetEvent dto = new GetEvent();
        dto.setDate(DateFormat.format(DateFormatEnum.ptBR, entity.getDate()));
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setHouse(transform(entity.getHouse()));
        return (T) dto;
    }

    @Override
    public Event revert(Object o) {
        Event event = new Event();

        if (o instanceof PersistEvent) {
            PersistEvent persistEvent = (PersistEvent) o;
            event.setName(persistEvent.getName());
            event.setDate(DateFormat.format(DateFormatEnum.Default, persistEvent.getDate()));
            event.setHouse(houseService.getHouseById(persistEvent.getHouseId()));
        }

        return event;
    }
    
}
