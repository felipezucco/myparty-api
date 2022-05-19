/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.dto.ticket;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.TicketBatchConverter;
import lombok.Data;

/**
 *
 * @author Felipe Zucco
 */
@Data
@DataConverterType(value = TicketBatchConverter.class, dto = true)
public class TicketBatchDTO {
    
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private Integer firstNumber;
    
}
