package com.example.kochbuch;

import java.io.Serializable;

public class Ingredients implements Serializable {

    //private IngredientsType ingredientsType;
    private int amount;

    private String name; //TODO in IngredientsType auslagern mit stammdaten für dropdown
    private String unit; //TODO in IngredientsType auslagern mit stammdaten für dropdown

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
