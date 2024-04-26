package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.models.CheckStore;
import com.example.demo.models.Employee;
import com.example.demo.repo.CardRepository;
import com.example.demo.repo.CheckStoreRepository;
import com.example.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class CheckStoreController {
    @Autowired
    private CheckStoreRepository checkStoreRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/checkStore")
    public String checkStoreMain(Model model) {
        Iterable<CheckStore> checkStores = checkStoreRepository.findAll();
        model.addAttribute("checkStores", checkStores);
        return "checkStore";
    }

    @GetMapping("/checkStore/add")
    public String checkStoreAdd(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        Iterable<Card> cards = cardRepository.findAll();
        model.addAttribute("cards", cards);
        return "checkStoreAdd";
    }

    @PostMapping("/checkStore/add")
    public String checkStorePostAdd(@RequestParam Long id_empl, @RequestParam String id_card, @RequestParam double sum) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        double vat = sum * 0.2;
        CheckStore checkStore = new CheckStore(id_empl, id_card, currentDateTime, sum, vat);
        checkStoreRepository.save(checkStore);
        return "redirect:/checkStore";
    }

    @GetMapping("/checkStore/delete")
    public String checkStoreDelete(Model model) {
        Iterable<CheckStore> checkStores = checkStoreRepository.findAll();
        model.addAttribute("checkStores", checkStores);
        return "checkStoresDelete";
    }

    @PostMapping("/checkStore/delete")
    public String checkStoresPostDelete(@RequestParam Long id, Model model) {
        Optional<CheckStore> optionalCheckStore = checkStoreRepository.findById(id);
        if (optionalCheckStore.isPresent()) {
            CheckStore checkStore = optionalCheckStore.get();
            checkStoreRepository.delete(checkStore);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/checkStore";
    }
}
