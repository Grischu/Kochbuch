package com.example.kochbuch.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.kochbuch.Database.IngredientsConverter;

import java.io.Serializable;

@Entity
public class Recipe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String descirption;
    private int difficulty;
    private String time;
    @TypeConverters(IngredientsConverter.class)
    private RecipeIngredients recipeIngredients;
    private int number;

    private byte[] image;

    public Recipe(String title, String descirption, int difficulty, String time, byte [] image, int number, RecipeIngredients recipeIngredients) {
        this.title = title;
        this.descirption = descirption;
        this.difficulty = difficulty;
        this.time = time;
        this.image = image;
        this.recipeIngredients = recipeIngredients;
        this.number = number;
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

    public String getTime() {
        return time;
    }

    public byte [] getImage() {
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setImage(byte [] image) {
        this.image = image;
    }

    public RecipeIngredients getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(RecipeIngredients recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
