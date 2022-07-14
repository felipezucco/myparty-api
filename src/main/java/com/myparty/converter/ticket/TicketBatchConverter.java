/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter.ticket;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.ticket.PersistTicketBatch;
import com.myparty.dto.ticket.GetTicketBatch;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.ticket.TicketBatch;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class TicketBatchConverter extends ConverterComponent implements DataConverterInterface<TicketBatch> {



    @Override
    public <T> T convert(TicketBatch entity, T destinationClass) {
        GetTicketBatch dto = new GetTicketBatch();

        dto.setFirstNumber(entity.getFirstNumber());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());

        return (T) dto;
    }

    @Override
    public TicketBatch revert(Object o) {
        TicketBatch ticketBatch = new TicketBatch();

        if (o instanceof PersistTicketBatch) {
            PersistTicketBatch ptb = (PersistTicketBatch) o;
            ticketBatch.setFirstNumber(ptb.getFirstNumber());
            ticketBatch.setName(ptb.getName());
            ticketBatch.setPrice(ptb.getPrice());
            ticketBatch.setQuantity(ptb.getQuantity());
        }

        return ticketBatch;
    }
}
