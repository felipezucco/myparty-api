/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.ticket.TicketBatchDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.TicketBatch;

/**
 *
 * @author Felipe Zucco
 */
public class TicketBatchConverter implements DataConverter<TicketBatch, TicketBatchDTO>{

    @Override
    public TicketBatchDTO convert(TicketBatch entity) {
        TicketBatchDTO dto = new TicketBatchDTO();

        dto.setFirstNumber(entity.getFirstNumber());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        
        return dto;
    }

    @Override
    public TicketBatch revert(TicketBatchDTO dto) {
        TicketBatch ticketBatch = new TicketBatch();
        
        ticketBatch.setFirstNumber(dto.getFirstNumber());
        ticketBatch.setId(dto.getId());
        ticketBatch.setName(dto.getName());
        ticketBatch.setPrice(dto.getPrice());
        ticketBatch.setQuantity(dto.getQuantity());
        
        return ticketBatch;
    }        
    
}
