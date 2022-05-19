/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.dto.ticket;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.TicketConverter;
import com.myparty.dto.EventDTO;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Felipe Zucco
 */
@Data
@DataConverterType(value = TicketConverter.class, dto = true)
public class TicketDTO {
    
    private Long id;
    private String name;
    private EventDTO event;
    private List<TicketBatchDTO> batchs;
    
}
