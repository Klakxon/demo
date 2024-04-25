package com.example.demo.controllers;

import com.example.demo.models.CheckStore;
import com.example.demo.repo.CheckStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class CheckStoreController {
    @Autowired
    private CheckStoreRepository checkStoreRepository;

    @GetMapping("/checkStore")
    public String checkStoreMain(Model model) {
        Iterable<CheckStore> checkStores = checkStoreRepository.findAll();
        model.addAttribute("checkStores", checkStores);
        return "checkStore";
    }

    @GetMapping("/checkStore/add")
    public String checkStoreAdd(Model model) {
        return "checkStoreAdd";
    }

    @PostMapping("/checkStore/add")
    public String checkStorePostAdd(@RequestParam String id, @RequestParam Long id_empl,
                                       @RequestParam String id_card, @RequestParam Date print_date,
                                       @RequestParam double sum, @RequestParam double vat) {
        CheckStore checkStore = new CheckStore(id, id_empl, id_card, print_date, sum, vat);
        checkStoreRepository.save(checkStore);
        return "redirect:/checkStore";
    }
}
