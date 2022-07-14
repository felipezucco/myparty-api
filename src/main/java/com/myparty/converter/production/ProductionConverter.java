package com.myparty.converter.production;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.production.PersistProduction;
import com.myparty.dto.production.GetProduction;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.production.Production;
import com.myparty.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductionConverter extends ConverterComponent implements DataConverterInterface<Production> {

    private EventService eventService;

    @Override
    public <T> T convert(Production entity, T destinationClass) {
        GetProduction dto = new GetProduction();

        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setStaffQuantity(entity.getStaffQuantity());
        dto.setProductionCost(transform(entity.getProductionCosts()));
        dto.setFinancial(transform(entity.getFinancial()));

        return (T) dto;
    }

    @Override
    public Production revert(Object o) {
        Production production = new Production();

        if (o instanceof PersistProduction) {
            PersistProduction pp = (PersistProduction) o;
            production.setName(pp.getName());
            production.setEvent(eventService.getEventById(pp.getEventId()));
            production.setStaffQuantity(pp.getStaffQuantity());
            production.setFinancial(transform(pp.getFinancial()));
            production.setProductionCosts(transform(pp.getProductionCost()));

            // Set production reference in productionCost
            production.getProductionCosts().forEach(productionCost -> productionCost.setProduction(production));
        }

        return production;
    }
}
