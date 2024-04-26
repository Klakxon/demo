package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CheckStore {
    public CheckStore(Long id_empl, String id_card, LocalDateTime print_date, double sum, double vat) {
        this.id_empl = id_empl;
        this.id_card = id_card;
        this.print_date = print_date;
        this.sum = sum;
        this.vat = vat;
    }
    public CheckStore() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_empl;
    private String id_card;
    private LocalDateTime print_date;
    private double sum, vat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_empl() {
        return id_empl;
    }

    public void setId_empl(Long id_empl) {
        this.id_empl = id_empl;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public LocalDateTime getPrint_date() {
        return print_date;
    }

    public void setPrint_date(LocalDateTime print_date) {
        this.print_date = print_date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
}
