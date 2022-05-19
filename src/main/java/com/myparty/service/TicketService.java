/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.service;

import com.myparty.model.Ticket;
import com.myparty.repository.TicketBatchRepository;
import com.myparty.repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Zucco
 */
@Service
public class TicketService {
    
    @Autowired
    private TicketRepository repository;
    
    @Autowired
    private TicketBatchRepository batchRepository;
    
    public void persistTicket(Ticket ticket) {
        repository.save(ticket);
    }
    
    public List<Ticket> getTicketByEventId(Long id) {
        return repository.findTicketByEventId(id);
    }
    
    public void deleteTicketBatchById(Long id) {
        batchRepository.deleteById(id);
    }
    
    public void deleteTicketById(Long id) {
        repository.deleteById(id);
    }
}
