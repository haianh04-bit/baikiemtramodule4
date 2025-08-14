package com.codegym.controller;

import com.codegym.model.Promotion;
import com.codegym.repository.PromotionRepository;
import com.codegym.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public String listPromotions(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Promotion> promotions;
        if (keyword != null && !keyword.isEmpty()) {
            promotions = promotionService.searchByName(keyword);
        } else {
            promotions = promotionService.findByDeletedFalse();
        }
        model.addAttribute("promotions", promotions);
        model.addAttribute("keyword", keyword);
        return "promotion/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "promotion/create";
    }

    @PostMapping("/create")
    public String createPromotion(@Valid @ModelAttribute("promotion") Promotion promotion,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "promotion/create";
        }
        promotion.setDeleted(false); // đảm bảo trường deleted mặc định false
        promotionService.save(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Promotion promotion = promotionService.findById(id);
        if (promotion == null) {
            return "redirect:/promotions";
        }
        model.addAttribute("promotion", promotion);
        return "promotion/edit";
    }

    @PostMapping("/edit")
    public String updatePromotion(@Valid @ModelAttribute("promotion") Promotion promotion,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "promotion/edit";
        }
        promotionService.save(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable("id") Long id) {
        promotionService.softDelete(id);
        return "redirect:/promotions";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Promotion> getPromotionsApi() {
        return promotionService.findAllActive();
    }
}

