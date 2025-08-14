package com.codegym.repository;

import com.codegym.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByDiscount(double discount);
}
