package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/products")
    public String productsMain(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String productsAdd(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String productsPostAdd(@RequestParam Long id_category, @RequestParam String name, @RequestParam String description) {
        Product existingProduct = productRepository.findByName(name);
        if (existingProduct != null) {
            return "redirect:/error-page";
        } else {
            Product product = new Product(id_category, name, description);
            productRepository.save(product);
            return "redirect:/products";
        }
    }
}
