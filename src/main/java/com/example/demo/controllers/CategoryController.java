package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public String categoryMain(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("/category/add")
    public String categoryAdd(Model model) {
        return "categoryAdd";
    }

    @PostMapping("/category/add")
    public String categoryPostAdd(@RequestParam String name, Model model) {
        // Перевірка, чи існує вже категорія з такою ж назвою
        Category existingCategory = categoryRepository.findByName(name);

        if (existingCategory != null) {
            // Якщо категорія з такою ж назвою вже існує, перенаправляємо користувача на сторінку error-page
            return "redirect:/error-page";
        } else {
            // Якщо категорія з такою назвою не існує, додаємо нову категорію
            Category category = new Category(name);
            categoryRepository.save(category);
            return "redirect:/category";
        }
    }

    @GetMapping("/category/edit")
    public String categoryEdit(Model model) {
        // Отримуємо список усіх категорій з бази даних
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories); // Передаємо список категорій в модель
        return "categoryEdit"; // Відображаємо сторінку редагування категорії
    }

    @PostMapping("/category/edit")
    public String categoryPostEdit(@RequestParam Long id, @RequestParam String name, Model model) {
        // Отримуємо категорію за вказаним ID
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(name); // Змінюємо назву категорії
            categoryRepository.save(category); // Зберігаємо змінену категорію
        } else {
            // Якщо категорія з вказаним ID не знайдена, то можна відобразити помилку або зробити щось інше
            // Наприклад, ви можете перенаправити користувача на іншу сторінку або повідомити про помилку
            return "redirect:/error-page";
        }
        return "redirect:/category"; // Перенаправляємо користувача на сторінку зі списком категорій
    }

    @GetMapping("/category/delete")
    public String categoryDelete(Model model) {
        // Отримуємо список усіх категорій з бази даних
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories); // Передаємо список категорій в модель
        return "categoryDelete"; // Відображаємо сторінку видалення категорії
    }

    @PostMapping("/category/delete")
    public String categoryPostDelete(@RequestParam Long id, Model model) {
        // Отримуємо категорію за вказаним ID
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            categoryRepository.delete(category); // Видаляємо категорію з бази даних
        } else {
            // Якщо категорія з вказаним ID не знайдена, то можна відобразити помилку або зробити щось інше
            // Наприклад, ви можете перенаправити користувача на іншу сторінку або повідомити про помилку
            return "redirect:/error-page";
        }
        return "redirect:/category"; // Перенаправляємо користувача на сторінку зі списком категорій
    }

    @GetMapping("/category/numOfProducts")
    public String categoryNumOfProducts(Model model) {
        List<Object[]> categories = categoryRepository.numberOfProductsInEachCategory();
        model.addAttribute("categories", categories);
        return "categoryNumOfProducts";
    }

    @GetMapping("/category/numOfProductsOne")
    public String categoryNumOfProductsOne(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoryNumOfProductsOne";
    }

    @PostMapping("/category/numOfProductsOne")
    public String categoryNumOfProductsOnePost(@RequestParam String name, Model model) {
        List<Object[]> categories = categoryRepository.numberOfProductsInCategory(name);
        model.addAttribute("categories", categories);
        return "categoryNumOfProductsOneRes";
    }

    @GetMapping("/category/promotional")
    public String categoryPromotional(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoryPromotional";
    }

    @PostMapping("/category/promotional")
    public String categoryPromotionalPost(@RequestParam String name, Model model) {
        List<Object[]> categories = categoryRepository.findPromotional(name);
        model.addAttribute("categories", categories);
        return "categoryPromotionalRes";
    }

    @GetMapping("/category/promotionalNot")
    public String categoryPromotionalNot(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoryNotPromotional";
    }

    @PostMapping("/category/promotionalNot")
    public String categoryPromotionalNotPost(@RequestParam String name, Model model) {
        List<Object[]> categories = categoryRepository.findNotPromotional(name);
        model.addAttribute("categories", categories);
        return "categoryNotPromotionalRes";
    }
}
