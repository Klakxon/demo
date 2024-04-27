package com.example.demo.controllers;

import com.example.demo.models.Login;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private LoginRepository loginRepository; // Предполагается, что у вас есть LoginRepository для взаимодействия с сущностью Login
    @Autowired
    private EmployeeRepository employeeRepository; // Предполагается, что у вас есть EmployeeRepository для взаимодействия с сущностью Employee

    private Long userId;
    private String role;

    @GetMapping("/login")
    public String loginMain() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@RequestParam String login, @RequestParam String password, Model model) {
        Login optionalLogin = loginRepository.findByLoginAndPassword(login, password);

        if (optionalLogin == null) {
            // Якщо немає відповідності логіна і пароля, відобразити помилку
            model.addAttribute("error", "Неправильний логін чи пароль");
            return "login";
        } else {
            // Якщо логін і пароль відповідають, зберегти id користувача
            userId = optionalLogin.getId();
            role = employeeRepository.findRoleById(userId);
            model.addAttribute("role", role);
            saveRoleAndUserIdToFile(role, userId);
            return "redirect:/user"; // Перенаправлення на сторінку інформації про працівника
        }
    }

    // Метод для збереження значень змінних role і userId в текстовий файл
    private void saveRoleAndUserIdToFile(String role, Long userId) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("role_and_userId.txt"));
            writer.write(role + "\n" + userId);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
