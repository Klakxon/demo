package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    public Product(Long id, Long id_category, String name, String description) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
        this.description = description;
    }

    public Product(Long id_category, String name, String description) {
        this.id_category = id_category;
        this.name = name;
        this.description = description;
    }
    public Product(){
    }

    @Id
    private Long id;
    private Long id_category;
    private String name, description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
