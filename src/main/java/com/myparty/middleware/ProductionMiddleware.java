package com.myparty.middleware;

import com.myparty.dto.production.GetProduction;
import com.myparty.model.production.Production;
import com.myparty.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionMiddleware extends RootMiddleware {

    @Autowired
    private ProductionService productionService;

    public GetProduction persistProduction(GetProduction getProduction) {
        Production production = productionService.persistProduction(convert(getProduction));
        return convert(production);
    }

    public List<GetProduction> getProductionByEventId(Long eventId) {
        //return convert(productionService.getProductionByEventId(eventId));
        return convert(productionService.getProductionByEventId(eventId));
    }

}
