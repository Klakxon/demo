package com.example.demo.controllers;

import com.example.demo.models.StoreProduct;
import com.example.demo.repo.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreProductController {
    @Autowired
    private StoreProductRepository storeProductRepository;

    @GetMapping("/store_products")
    public String storeProductsMain(Model model) {
        Iterable<StoreProduct> storeProducts = storeProductRepository.findAll();
        model.addAttribute("storeProducts", storeProducts);
        return "store_products";
    }

    @GetMapping("/store_products/add")
    public String storeProductsAdd(Model model) {
        return "store_productsAdd";
    }

    @PostMapping("/store_products/add")
    public String storeProductsPostAdd(@RequestParam String UPC, @RequestParam String UPC_prom,
                                       @RequestParam Long id_product, @RequestParam double selling_price,
                                       @RequestParam int products_number, @RequestParam String promotional_product) {
        boolean isPromotional = promotional_product.equals("yes");
        StoreProduct storeProduct = new StoreProduct(UPC, UPC_prom, id_product, selling_price, products_number, isPromotional);
        storeProductRepository.save(storeProduct);
        return "redirect:/store_products";
    }
}
