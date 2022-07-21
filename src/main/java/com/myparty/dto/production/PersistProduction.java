package com.myparty.dto.production;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionConverter;
import com.myparty.dto.financial.GetFinancial;
import com.myparty.dto.financial.PersistFinancial;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@DataConverterType(ProductionConverter.class)
public class PersistProduction implements Serializable {

    private String name;
    private Integer staffQuantity;
    private Long eventId;
    private PersistFinancial financial;
    private List<PersistProductionCost> productionCost;
}
