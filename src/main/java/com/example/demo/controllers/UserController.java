package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class UserController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/user")
    public String userMain(Model model) {
        Iterable<Employee> users = employeeRepository.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/user/add")
    public String categoryAdd(Model model) {
        return "userAdd";
    }

    @PostMapping("/user/add")
    public String categoryPostAdd(@RequestParam Long id,
                                  @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic,
                                  @RequestParam String role, @RequestParam double salary,
                                  @RequestParam Date date_of_birth, @RequestParam Date date_of_start,
                                  @RequestParam String phone, @RequestParam String city, @RequestParam String street,
                                  @RequestParam String zip, Model model) {
        Employee employee = new Employee(id, surname, name, patronymic, role, phone, city, street, zip, salary, date_of_birth, date_of_start);
        employeeRepository.save(employee);
        return "redirect:/user";
    }
}
