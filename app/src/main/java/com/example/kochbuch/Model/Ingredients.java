package com.example.kochbuch.Model;

import java.io.Serializable;

public class Ingredients implements Serializable {
    private double amount;
    private String name;
    private String unit;

    //Double for Null-Check in Validator
    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
