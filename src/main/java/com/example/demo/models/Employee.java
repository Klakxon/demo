package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Employee {
    public Employee(Long id, String surname, String name, String patronymic, String role, String phone, String city,
                    String street, String zip, double salary, Date date_of_birth, Date date_of_start) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.role = role;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.salary = salary;
        this.date_of_birth = date_of_birth;
        this.date_of_start = date_of_start;
    }
    public Employee(String surname, String name, String patronymic, String role, String phone, String city,
                    String street, String zip, double salary, Date date_of_birth, Date date_of_start) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.role = role;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.salary = salary;
        this.date_of_birth = date_of_birth;
        this.date_of_start = date_of_start;
    }
    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname, name, patronymic, role, phone, city, street, zip;
    private double salary;
    private Date date_of_birth, date_of_start;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Date getDate_of_start() {
        return date_of_start;
    }

    public void setDate_of_start(Date date_of_start) {
        this.date_of_start = date_of_start;
    }
}
