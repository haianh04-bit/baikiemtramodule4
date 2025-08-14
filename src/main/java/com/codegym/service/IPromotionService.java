package com.codegym.service;

import com.codegym.model.Promotion;
import java.util.List;

public interface IPromotionService {
    List<Promotion> findAll();
    Promotion findById(Long id);
    void save(Promotion promotion);
    void delete(Long id);
    List<Promotion> findByDiscount(double discount);
}
