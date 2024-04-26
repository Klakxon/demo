package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String categoryPostAdd(@RequestParam String name, RedirectAttributes redirectAttributes, Model model) {
        // Перевірка, чи існує вже категорія з такою ж назвою
        Category existingCategory = categoryRepository.findByName(name);

        if (existingCategory != null) {
            // Якщо категорія з такою ж назвою вже існує, перенаправляємо користувача на сторінку error-page
            return "redirect:/error-page";
        } else {
            // Якщо категорія з такою назвою не існує, додаємо нову категорію
            Category category = new Category(name);
            categoryRepository.save(category);
            return "redirect:/category";
        }
    }


}
