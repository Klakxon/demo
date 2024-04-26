package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {
    @GetMapping("/error-page")
    public String errorMain(Model model, @RequestParam(required = false) String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }
}
