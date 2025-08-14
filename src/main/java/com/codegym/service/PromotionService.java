package com.codegym.service;

import com.codegym.model.Promotion;
import java.util.List;

public interface PromotionService {
    List<Promotion> findAllActive();
    Promotion findById(Long id);
    Promotion save(Promotion promotion);
    void softDelete(Long id);
    List<Promotion> searchByName(String name);

    List<Promotion> findByDeletedFalse();
}