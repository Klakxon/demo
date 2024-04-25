package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.models.Product;
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

    @GetMapping("/products")
    public String productsMain(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String productsAdd(Model model) {
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String productsPostAdd(@RequestParam Long id, @RequestParam Long id_category,
                                  @RequestParam String name, @RequestParam String description) {
        Product product = new Product(id, id_category, name, description);
        productRepository.save(product);
        return "redirect:/products";
    }
}
