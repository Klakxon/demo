package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.repo.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/clients")
    public String clientMain(Model model) {
        Iterable<Card> clients = cardRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/clients/add")
    public String clientAdd(Model model) {
        return "clientsAdd";
    }

    @PostMapping("/clients/add")
    public String clientPostAdd(@RequestParam String id, @RequestParam String surname, @RequestParam String name,
                                @RequestParam String patronymic, @RequestParam String phone, @RequestParam String city,
                                @RequestParam String street, @RequestParam String zip, @RequestParam int percent, Model model) {
        Card card = new Card(id, surname, name, patronymic, phone, city, street, zip, percent);
        cardRepository.save(card);
        return "redirect:/clients";
    }
}
