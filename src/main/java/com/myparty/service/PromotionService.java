package com.myparty.service;

import com.myparty.model.Promotion;
import com.myparty.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService {

    private PromotionRepository promotionRepository;

    public Promotion persistPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion getPromotionById(Long id) {
        return promotionRepository.getById(id);
    }

    public void removePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    public List<Promotion> getPromotionByEventId(Long id) {
        return promotionRepository.findByEventId(id);
    }
}
