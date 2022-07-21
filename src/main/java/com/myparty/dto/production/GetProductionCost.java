package com.myparty.dto.production;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionCostConverter;
import com.myparty.dto.financial.GetFinancial;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(ProductionCostConverter.class)
public class GetProductionCost implements Serializable {

    private Long id;
    private String name;
    private Double quantity;
    private GetFinancial financial;
    private Long productionId;

}
