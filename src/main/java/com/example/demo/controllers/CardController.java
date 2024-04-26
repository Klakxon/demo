package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.models.Category;
import com.example.demo.repo.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
    public String clientPostAdd(@RequestParam String surname, @RequestParam String name,
                                @RequestParam String patronymic, @RequestParam String phone, @RequestParam String city,
                                @RequestParam String street, @RequestParam String zip, @RequestParam int percent, Model model) {
        Card existingCard = cardRepository.findByPhone(phone);
        if (existingCard != null) {
            return "redirect:/error-page";
        } else {
            Card card = new Card(surname, name, patronymic, phone, city, street, zip, percent);
            cardRepository.save(card);
            return "redirect:/clients";
        }
    }

    @GetMapping("/clients/edit")
    public String clientsEdit(Model model) {
        Iterable<Card> cards = cardRepository.findAll();
        model.addAttribute("clients", cards);
        return "clientsEdit";
    }

    @PostMapping("/clients/edit")
    public String clientsPostEdit(@RequestParam Long id, @RequestParam String surname, @RequestParam String name,
                                  @RequestParam String patronymic, @RequestParam String phone, @RequestParam String city,
                                  @RequestParam String street, @RequestParam String zip, @RequestParam int percent, Model model) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setSurname(surname);
            card.setName(name);
            card.setName(name);
            card.setPatronymic(patronymic);
            card.setPhone(phone);
            card.setCity(city);
            card.setStreet(street);
            card.setZip(zip);
            card.setPercent(percent);
            cardRepository.save(card);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/clients";
    }

    @GetMapping("/clients/delete")
    public String clientsDelete(Model model) {
        Iterable<Card> cards = cardRepository.findAll();
        model.addAttribute("clients", cards);
        return "clientsDelete";
    }

    @PostMapping("/clients/delete")
    public String clientsPostDelete(@RequestParam Long id, Model model) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            cardRepository.delete(card);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/clients";
    }
}
