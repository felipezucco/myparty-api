package com.myparty.controller.middleware;


import com.myparty.dto.ticket.TicketDTO;
import com.myparty.service.TicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Felipe Zucco
 */
@Service
public class TicketMiddleware extends RootMiddleware {
    
    @Autowired
    private TicketService service;
    
    public void persistTicket(TicketDTO dto) {
        service.persistTicket(convert(dto));
    }
    
    public List<TicketDTO> getTicketByEventId(Long id) {
        return convert(service.getTicketByEventId(id));
    }
    
    public void deleteTicketBatchById(Long id) {
        service.deleteTicketBatchById(id);
    }
    
    public void deleteTicketById(Long id) {
        service.deleteTicketById(id);
    }
}
