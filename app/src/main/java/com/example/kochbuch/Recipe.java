package com.example.kochbuch;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Recipe {
    @PrimaryKey
    private int id;
    private String title;
    private String descirption;
    private int difficulty;
    private int time;
    //private List<String> ingredients;

    private int image;

    public Recipe(int id, String title, String descirption, int difficulty, int time, int image) {
        this.id = id;
        this.title = title;
        this.descirption = descirption;
        this.difficulty = difficulty;
        this.time = time;
        //this.ingredients = ingredients;
        this.image = image;
    }

    public void changeTitle(String text) {
        title = text;
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

    /*public List<String> getIngredients() {
        return ingredients;
    }*/

    public int getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
