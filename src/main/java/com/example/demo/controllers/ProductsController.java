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

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/products/edit")
    public String productsEdit(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "productsEdit";
    }

    @PostMapping("/products/edit")
    public String productsPostEdit(@RequestParam Long id, @RequestParam Long id_category, @RequestParam String name,
                                  @RequestParam String description, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setId_category(id_category);
            product.setName(name);
            product.setDescription(description);
            productRepository.save(product);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/products";
    }

    @GetMapping("/products/delete")
    public String productsDelete(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "productsDelete";
    }

    @PostMapping("/products/delete")
    public String productsPostDelete(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/products";
    }

    @GetMapping("/products/sort")
    public String productsSort(Model model) {
        List<Object[]> products = productRepository.sortProduct();
        model.addAttribute("products", products);
        return "sort";
    }
}
