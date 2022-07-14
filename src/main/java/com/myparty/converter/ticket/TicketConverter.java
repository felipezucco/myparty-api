/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter.ticket;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.ticket.PersistTicket;
import com.myparty.dto.ticket.GetTicket;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.ticket.Ticket;
import com.myparty.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
@AllArgsConstructor
public class TicketConverter extends ConverterComponent implements DataConverterInterface<Ticket> {

    private EventService eventService;

    @Override
    public <T> T convert(Ticket entity, T destinationClass) {
        GetTicket dto = new GetTicket();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEvent(transform(entity.getEvent()));
        dto.setBatchs(transform(entity.getBatchs()));

        return (T) dto;
    }

    @Override
    public Ticket revert(Object o) {
        Ticket ticket = new Ticket();

        if (o instanceof PersistTicket) {
            PersistTicket pt = (PersistTicket) o;
            ticket.setName(pt.getName());
            ticket.setEvent(eventService.getEventById(pt.getEventId()));
            ticket.setBatchs(transform(pt.getBatchs()));
            ticket.getBatchs().forEach(ticketBatch -> ticketBatch.setTicket(ticket));
        }

        return ticket;
    }
}
