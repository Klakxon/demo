package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public String categoryMain(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("/category/add")
    public String categoryAdd(Model model) {
        return "categoryAdd";
    }

    @PostMapping("/category/add")
    public String categoryPostAdd(@RequestParam Long id, @RequestParam String name, Model model) {
        Category category = new Category(id, name);
        categoryRepository.save(category);
        return "redirect:/category";
    }
}
