package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class CheckStore {
    public CheckStore(String id, Long id_empl, String id_card, Date print_date, double sum, double vat) {
        this.id = id;
        this.id_empl = id_empl;
        this.id_card = id_card;
        this.print_date = print_date;
        this.sum = sum;
        this.vat = vat;
    }
    public CheckStore() {
    }

    @Id
    private String id;
    private Long id_empl;
    private String id_card;
    private Date print_date;
    private double sum, vat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getPrint_date() {
        return print_date;
    }

    public void setPrint_date(Date print_date) {
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
