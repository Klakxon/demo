package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.models.StoreProduct;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StoreProductController {
    @Autowired
    private StoreProductRepository storeProductRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/storeProducts")
    public String storeProductsMain(Model model) {
        Iterable<StoreProduct> storeProducts = storeProductRepository.findAll();
        model.addAttribute("storeProducts", storeProducts);
        return "storeProducts";
    }

    @GetMapping("/storeProducts/add")
    public String storeProductsAdd(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "storeProductsAdd";
    }

    @PostMapping("/storeProducts/add")
    public String storeProductsPostAdd(@RequestParam String UPC, @RequestParam String UPC_prom,
                                       @RequestParam Long id_product, @RequestParam double selling_price,
                                       @RequestParam int products_number, @RequestParam String promotional_product, Model model) {
        StoreProduct existingStoreProduct = storeProductRepository.findById_product(id_product);
        if (existingStoreProduct != null) {
            return "redirect:/error-page";
        } else {
            boolean isPromotional = promotional_product.equals("yes");
            StoreProduct storeProduct = new StoreProduct(UPC, UPC_prom, id_product, selling_price, products_number, isPromotional);
            storeProductRepository.save(storeProduct);
            return "redirect:/storeProducts";
        }
    }

    @GetMapping("/storeProducts/edit")
    public String storeProductsEdit(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        Iterable<StoreProduct> storeProducts = storeProductRepository.findAll();
        model.addAttribute("storeProducts", storeProducts);
        return "storeProductsEdit";
    }

    @PostMapping("/storeProducts/edit")
    public String storeProductsPostEdit(@RequestParam String UPC, @RequestParam String UPC_prom,
                                        @RequestParam Long id_product, @RequestParam double selling_price,
                                        @RequestParam int products_number, @RequestParam String promotional_product, Model model) {
        Optional<StoreProduct> optionalStoreProduct = storeProductRepository.findById(UPC);
        if (optionalStoreProduct.isPresent()) {
            StoreProduct storeProduct = optionalStoreProduct.get();
            storeProduct.setUPC_prom(UPC_prom);
            storeProduct.setId_product(id_product);
            storeProduct.setSelling_price(selling_price);
            storeProduct.setProducts_number(products_number);
            boolean isPromotional = promotional_product.equals("yes");
            storeProduct.setPromotional_product(isPromotional);
            storeProductRepository.save(storeProduct);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/storeProducts";
    }

    @GetMapping("/storeProducts/delete")
    public String storeProductsDelete(Model model) {
        Iterable<StoreProduct> storeProducts = storeProductRepository.findAll();
        model.addAttribute("storeProducts", storeProducts);
        return "storeProductsDelete";
    }

    @PostMapping("/storeProducts/delete")
    public String storeProductsPostDelete(@RequestParam String UPC, Model model) {
        Optional<StoreProduct> optionalStoreProduct = storeProductRepository.findById(UPC);
        if (optionalStoreProduct.isPresent()) {
            StoreProduct storeProduct = optionalStoreProduct.get();
            storeProductRepository.delete(storeProduct);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/storeProducts";
    }
}
