package com.example.kochbuch;

import java.io.Serializable;
import java.util.List;

public class RecipeIngredients implements Serializable {

    List<Ingredients> ingredientsList;

    public RecipeIngredients(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
