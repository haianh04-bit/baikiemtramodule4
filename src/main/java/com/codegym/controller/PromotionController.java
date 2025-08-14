package com.codegym.controller;

import com.codegym.model.Promotion;
import com.codegym.service.IPromotionService;
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
    private IPromotionService promotionService;

    @GetMapping
    public String list(@RequestParam(required = false) Double discount, Model model) {
        List<Promotion> promotions;
        if (discount != null) {
            promotions = promotionService.findByDiscount(discount);
        } else {
            promotions = promotionService.findAll();
        }
        model.addAttribute("promotions", promotions);
        return "promotion/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "promotion/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("promotion") Promotion promotion,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "promotion/create";
        }
        promotionService.save(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("promotion", promotionService.findById(id));
        return "promotion/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("promotion") Promotion promotion,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "promotion/edit";
        }
        promotionService.save(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        promotionService.delete(id);
        return "redirect:/promotions";
    }
}
