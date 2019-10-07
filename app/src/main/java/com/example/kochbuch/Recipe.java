package com.example.kochbuch;

import java.util.List;

public class Recipe {

    private int id;
    private String title;
    private String descirption;
    private int difficulty;
    private int time;
    private List<String> ingredients;
    private int image;

    public Recipe(int id, String title, String descirption, int difficulty, int time, List<String> ingredients, int image) {
        this.id = id;
        this.title = title;
        this.descirption = descirption;
        this.difficulty = difficulty;
        this.time = time;
        this.ingredients = ingredients;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescirption() {
        return descirption;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getImage() {
        return image;
    }
}
