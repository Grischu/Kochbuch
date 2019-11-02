package com.example.kochbuch;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class Recipe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String descirption;
    private int difficulty;
    private int time;
    @TypeConverters(IngredientsConverter.class)
    private Ingredients ingredients;

    private int image;

    public Recipe(String title, String descirption, int difficulty, int time, int image, Ingredients ingredients) {
        this.title = title;
        this.descirption = descirption;
        this.difficulty = difficulty;
        this.time = time;
        this.image = image;
        this.ingredients = ingredients;
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

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }
}
