package com.myparty.dto.production;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionCostConverter;
import com.myparty.dto.FinancialDTO;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(ProductionCostConverter.class)
public class PersistProductionCost implements Serializable {

    private String name;
    private Double quantity;
    private FinancialDTO financial;
    private Long productionId;

}
