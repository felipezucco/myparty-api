package com.myparty.controller;

import com.myparty.dto.promotion.GetPromotion;
import com.myparty.dto.promotion.PersistPromotion;
import com.myparty.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController extends ControllerComponent {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void persistPromotion(@RequestBody PersistPromotion persistPromotion) {
        promotionService.persistPromotion(_8(persistPromotion));
    }

    @GetMapping("/event/{id}")
    private ResponseEntity<List<GetPromotion>> getPromotionByEventId(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(_8(promotionService.getPromotionByEventId(eventId), GetPromotion.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removePromotion(@PathVariable("id") Long id) {
        promotionService.removePromotion(id);
    }
}
