package com.example.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

    // Конструктори, геттери та сеттери

    public Login() {
        // Порожній конструктор (може бути необхідним для JPA)
    }

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Геттери та сеттери для полів

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Перевизначення методу toString() для зручного виводу об'єктів Login

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

