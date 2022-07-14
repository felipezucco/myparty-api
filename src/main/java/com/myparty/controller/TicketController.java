package com.myparty.controller;

import com.myparty.dto.ticket.PersistTicket;
import com.myparty.middleware.TicketMiddleware;
import com.myparty.dto.ticket.GetTicket;
import java.util.List;

import com.myparty.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController extends ControllerComponent {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public void persistEntity(@RequestBody PersistTicket dto) {
        ticketService.persistTicket(_8(dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<GetTicket>> getTicketByEventId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(ticketService.getTicketByEventId(id)));
    }
    
    @DeleteMapping("/batch/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicketBatchById(@PathVariable("id") Long id) {
        ticketService.deleteTicketBatchById(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicketById(@PathVariable("id") Long id) {
        ticketService.deleteTicketById(id);
    }
    
}
