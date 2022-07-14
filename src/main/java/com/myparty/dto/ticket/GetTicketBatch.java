/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.dto.ticket;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ticket.TicketBatchConverter;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Felipe Zucco
 */
@Data
@DataConverterType(TicketBatchConverter.class)
public class GetTicketBatch implements Serializable {
    
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private Integer firstNumber;
    
}
