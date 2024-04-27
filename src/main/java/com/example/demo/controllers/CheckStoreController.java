package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class CheckStoreController {
    @Autowired
    private CheckStoreRepository checkStoreRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StoreProductRepository storeProductRepository;


    @GetMapping("/checkStore")
    public String checkStoreMain(Model model) {
        Iterable<CheckStore> checkStores = checkStoreRepository.findAll();
        model.addAttribute("checkStores", checkStores);
        return "checkStore";
    }

    @GetMapping("/checkStore/add")
    public String checkStoreAdd(Model model) {
        Iterable<Card> cards = cardRepository.findAll();
        model.addAttribute("cards", cards);
        String role = getRoleFromRoleAndUserIdFile();
        model.addAttribute("role", role);
        return "checkStoreAdd";
    }

    @PostMapping("/checkStore/add")
    public String checkStorePostAdd(@RequestParam String id_card, @RequestParam double sum) {
        // Зчитуємо інформацію про користувача з файлу
        Long id_empl = getIdFromRoleAndUserIdFile();

        LocalDateTime currentDateTime = LocalDateTime.now();
        double vat = sum * 0.2;

        CheckStore checkStore = new CheckStore(id_empl, id_card, currentDateTime, sum, vat);
        checkStoreRepository.save(checkStore);

        return "redirect:/checkStore";
    }

    private Long getIdFromRoleAndUserIdFile() {
        try {
            File file = new File("role_and_userId.txt");
            Scanner scanner = new Scanner(file);
            // Зчитуємо рядок з інформацією про посаду
            String role = scanner.nextLine();
            // Зчитуємо рядок з інформацією про ідентифікатор користувача
            String userId = scanner.nextLine();
            scanner.close();

            // Якщо роль - "Касир", повертаємо ідентифікатор користувача
            if (role.equals("Касир")) {
                return Long.parseLong(userId);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // За замовчуванням повертаємо null, якщо роль не "Касир"
        return null;
    }

    private String getRoleFromRoleAndUserIdFile() {
        String role = "";
        try {
            File file = new File("role_and_userId.txt");
            Scanner scanner = new Scanner(file);
            role = scanner.nextLine();
            scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return role;
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
