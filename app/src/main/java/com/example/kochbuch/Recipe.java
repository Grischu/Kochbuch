package com.example.kochbuch;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Recipe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String descirption;
    private int difficulty;
    private int time;
    //private List<Ingredients> ingredients;

    private int image;

    public Recipe(String title, String descirption, int difficulty, int time, int image) {
        this.title = title;
        this.descirption = descirption;
        this.difficulty = difficulty;
        this.time = time;
        //this.ingredients = ingredients;
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
