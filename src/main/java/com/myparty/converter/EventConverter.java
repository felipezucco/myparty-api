/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.EventDTO;
import com.myparty.enums.DateFormatEnum;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.Event;
import com.myparty.utils.DateFormat;

/**
 *
 * @author Felipe Zucco
 */
public class EventConverter implements DataConverter<Event, EventDTO>{
    
    private DataConverterImplement converter = new DataConverterImplement();

    @Override
    public EventDTO convert(Event entity) {
        EventDTO dto = new EventDTO();
        dto.setDate(DateFormat.format(DateFormatEnum.ptBR, entity.getDate()));
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setOrganization(converter.convert(entity.getOrganization()));
        dto.setHouse(converter.convert(entity.getHouse()));
        return dto;
    }

    @Override
    public Event revert(EventDTO dto) {
        Event event = new Event();
        event.setDate(DateFormat.format(DateFormatEnum.Default, dto.getDate()));
        event.setId(dto.getId());
        event.setOrganization(converter.convert(dto.getOrganization()));
        event.setHouse(converter.convert(dto.getHouse()));
        event.setName(dto.getName());
        return event;
    }
    
}
