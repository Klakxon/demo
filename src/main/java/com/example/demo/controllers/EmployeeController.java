package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Employee;
import com.example.demo.models.Product;
import com.example.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
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
    public String categoryPostAdd(@RequestParam String surname, @RequestParam String name,
                                  @RequestParam String patronymic, @RequestParam String role, @RequestParam double salary,
                                  @RequestParam Date date_of_birth, @RequestParam Date date_of_start,
                                  @RequestParam String phone, @RequestParam String city, @RequestParam String street,
                                  @RequestParam String zip, Model model) {
        Employee existingEmployee = employeeRepository.findByPhone(phone);
        if (existingEmployee != null) {
            return "redirect:/error-page";
        } else {
            Employee employee = new Employee(surname, name, patronymic, role, phone, city, street, zip, salary, date_of_birth, date_of_start);
            employeeRepository.save(employee);
            return "redirect:/user";
        }
    }

    @GetMapping("/user/edit")
    public String userEdit(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "userEdit";
    }

    @PostMapping("/user/edit")
    public String userPostEdit(@RequestParam Long id, @RequestParam String surname, @RequestParam String name,
                               @RequestParam String patronymic, @RequestParam String role, @RequestParam double salary,
                               @RequestParam Date date_of_birth, @RequestParam Date date_of_start,
                               @RequestParam String phone, @RequestParam String city, @RequestParam String street,
                               @RequestParam String zip, Model model) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setSurname(surname);
            employee.setName(name);
            employee.setPatronymic(patronymic);
            employee.setRole(role);
            employee.setSalary(salary);
            employee.setDate_of_birth(date_of_birth);
            employee.setDate_of_start(date_of_start);
            employee.setPhone(phone);
            employee.setCity(city);
            employee.setStreet(street);
            employee.setZip(zip);
            employeeRepository.save(employee);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/user";
    }

    @GetMapping("/user/delete")
    public String userDelete(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "userDelete";
    }

    @PostMapping("/user/delete")
    public String userPostDelete(@RequestParam Long id, Model model) {
        Optional<Employee> optionalUser = employeeRepository.findById(id);
        if (optionalUser.isPresent()) {
            Employee employee = optionalUser.get();
            employeeRepository.delete(employee);
        } else {
            return "redirect:/error-page";
        }
        return "redirect:/user";
    }

    @GetMapping("/user/notInvolved")
    public String userNotInvolved(Model model) {
        List<Object[]> employees = employeeRepository.findNotInvolved();
        model.addAttribute("employees", employees);
        return "userNotInvolved";
    }
}
