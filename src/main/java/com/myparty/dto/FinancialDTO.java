package com.myparty.dto;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.FinancialConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(FinancialConverter.class)
public class FinancialDTO implements Serializable {

    private Long id;
    private Double value;
}
