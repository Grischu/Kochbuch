package com.example.kochbuch.Database;

import android.arch.persistence.room.TypeConverter;

import com.example.kochbuch.Model.Ingredients;
import com.example.kochbuch.Model.RecipeIngredients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientsConverter {

    @TypeConverter
    public RecipeIngredients storedStringToIngredients(String s) {
        List<String> ingredientsString = Arrays.asList(s.split("\\s*,\\s*"));
        List<Ingredients> ingredientsList = new ArrayList<>();

        for(int i = 0; i < ingredientsString.size(); i+=3) {
            Ingredients ingredients = new Ingredients();
            ingredients.setName(ingredientsString.get(i));
            ingredients.setAmount(Double.parseDouble(ingredientsString.get(i+1)));
            ingredients.setUnit(ingredientsString.get(i+2));
            ingredientsList.add(ingredients);
        }
        return new RecipeIngredients(ingredientsList);
    }

    @TypeConverter
    public String ingredientsToStoredString(RecipeIngredients cl) {
        String value = "";

        for(Ingredients ingredients: cl.getIngredientsList()) {
            value += ingredients.getName() + ",";
            value += ingredients.getAmount() + ",";
            value += ingredients.getUnit() + ",";
        }

        return value;

    }

}
