package com.myparty.dto.production;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionConverter;
import com.myparty.dto.event.GetEvent;
import com.myparty.dto.financial.GetFinancial;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(ProductionConverter.class)
public class GetProduction implements Serializable {

    private Long id;
    private String name;
    private Integer staffQuantity;
    private GetEvent event;
    private GetFinancial financial;
    private List<GetProductionCost> productionCost;

}
