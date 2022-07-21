package com.myparty.dto.financial;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.FinancialConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(FinancialConverter.class)
public class PersistFinancial implements Serializable {

    private Double value;
}
