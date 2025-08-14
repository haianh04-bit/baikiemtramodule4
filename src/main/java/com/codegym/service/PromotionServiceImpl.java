package com.codegym.service;

import com.codegym.model.Promotion;
import com.codegym.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAllActive() {
        return promotionRepository.findByDeletedFalse();
    }

    @Override
    public Promotion findById(Long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if (promotion.isPresent() && !promotion.get().isDeleted()) {
            return promotion.get();
        }
        return null;
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void softDelete(Long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if (promotion.isPresent()) {
            Promotion p = promotion.get();
            p.setDeleted(true);
            promotionRepository.save(p);
        }
    }

    @Override
    public List<Promotion> searchByName(String name) {
        return promotionRepository.findByNameContainingAndDeletedFalse(name);
    }

    @Override
    public List<Promotion> findByDeletedFalse() {
        return promotionRepository.findByDeletedFalse();
    }
}