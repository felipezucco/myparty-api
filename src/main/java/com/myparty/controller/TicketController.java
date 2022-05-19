package com.myparty.controller;

import com.myparty.controller.middleware.TicketMiddleware;
import com.myparty.dto.ticket.TicketDTO;
import java.util.List;
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
public class TicketController {
	
    @Autowired
    private TicketMiddleware middleware;
    
    @PostMapping
    public void persistEntity(@RequestBody TicketDTO dto) {
        middleware.persistTicket(dto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<TicketDTO>> getTicketByEventId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(middleware.getTicketByEventId(id));
    }
    
    @DeleteMapping("/batch/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicketBatchById(@PathVariable("id") Long id) {
        middleware.deleteTicketBatchById(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicketById(@PathVariable("id") Long id) {
        middleware.deleteTicketById(id);
    }
    
}
