package com.myparty.converter.production;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.production.PersistProductionCost;
import com.myparty.dto.production.GetProductionCost;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.production.ProductionCost;
import com.myparty.service.ProductionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductionCostConverter extends ConverterComponent implements DataConverterInterface<ProductionCost> {

    private ProductionService productionService;

    @Override
    public <T> T convert(ProductionCost entity, T destinationClass) {
        GetProductionCost dto = new GetProductionCost();

        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setFinancial(transform(entity.getFinancial()));

        return (T) dto;
    }

    @Override
    public ProductionCost revert(Object o) {
        ProductionCost productionCost = new ProductionCost();

        if (o instanceof PersistProductionCost) {
            PersistProductionCost pc = (PersistProductionCost) o;
            productionCost.setName(pc.getName());
            productionCost.setQuantity(pc.getQuantity());
            productionCost.setFinancial(transform(pc.getFinancial()));

            if (pc.getProductionId() != null) {
                productionCost.setProduction(productionService.getProductionById(pc.getProductionId()));
            }
        }

        return productionCost;
    }
}
