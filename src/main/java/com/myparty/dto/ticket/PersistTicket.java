/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.dto.ticket;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ticket.TicketConverter;
import com.myparty.dto.event.GetEvent;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Felipe Zucco
 */
@Data
@DataConverterType(TicketConverter.class)
public class PersistTicket implements Serializable {

    private String name;
    private Long eventId;
    private List<PersistTicketBatch> batchs;
    
}
