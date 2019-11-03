package com.example.kochbuch;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientsConverter {

    @TypeConverter
    public RecipeIngredients storedStringToIngredients(String s) {
        List<String> ingredientsString = Arrays.asList(s.split("\\s*,\\s*"));
        List<Ingredients> ingredientsList = new ArrayList<>();

        /*List<String> name = new ArrayList<String>();
        List<Integer> amount = new ArrayList<Integer>();
        List<String> unit = new ArrayList<String>();*/

        String name = "";
        int amount = 0;
        String unit = "";


        /*for(int i = 1; i < ingredientsString.size()+1; i++) { //TODO Verschönern
            if((i % 3) == 0) {
                unit = ingredientsString.get(i-1);

                Ingredients ingredients = new Ingredients();
                ingredients.setName(name);
                ingredients.setAmount(amount);
                ingredients.setUnit(unit);

                ingredientsList.add(ingredients);

                continue;
            }
            if((i % 2) == 0) { //TODO Momentan geht es hier rein wenn das 4mal der loop aufgerufen wird -> 3 fors würde das problem lösen 1. counter 0 +3 / 2. counter 1 + 2 / 3. counter 3 + 3
                amount = Integer.parseInt(ingredientsString.get(i-1)); //TODO oder einfach 1 loop counter 0 dann einfach get(i+1, i+2) dann i+3 jeden loop
                continue;
            }
            name = ingredientsString.get(i-1);
        }*/

        for(int i = 0; i < ingredientsString.size(); i+=3) {
            Ingredients ingredients = new Ingredients();
            ingredients.setName(ingredientsString.get(i));
            ingredients.setAmount(Integer.parseInt(ingredientsString.get(i+1)));
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
