/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.ticket.TicketDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.Ticket;

/**
 *
 * @author Felipe Zucco
 */
public class TicketConverter implements DataConverter<Ticket, TicketDTO>{

    private DataConverterImplement converter = new DataConverterImplement();
    
    @Override
    public TicketDTO convert(Ticket entity) {
        TicketDTO dto = new TicketDTO();
        
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEvent(converter.convert(entity.getEvent()));
        dto.setBatchs(converter.convert(entity.getBatchs()));
        
        return dto;
    }

    @Override
    public Ticket revert(TicketDTO dto) {
        Ticket ticket = new Ticket();
        
        ticket.setId(dto.getId());
        ticket.setName(dto.getName());
        ticket.setEvent(converter.convert(dto.getEvent()));
        ticket.setBatchs(converter.convert(dto.getBatchs()));
        
        return ticket;
    }        
    
}
