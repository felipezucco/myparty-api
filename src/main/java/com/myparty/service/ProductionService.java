package com.myparty.service;

import com.myparty.model.production.Production;
import com.myparty.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionService {

    @Autowired
    private ProductionRepository productionRepository;

    public Production persistProduction(Production production) {
        productionRepository.save(production);
        return production;
    }

    public List<Production> getProductionByEventId(Long eventId) {
        return productionRepository.findByEventId(eventId);
    }

    public Production getProductionById(Long id) {
        return productionRepository.findById(id).orElse(null);
    }
}
