package com.example.kochbuch;

import android.arch.persistence.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class IngredientsConverter {
    @TypeConverter
    public Ingredients storedStringToLanguages(String value) {
        List<String> langs = Arrays.asList(value.split("\\s*,\\s*"));
        return new Ingredients(langs);
    }

    @TypeConverter
    public String languagesToStoredString(Ingredients cl) {
        String value = "";

        for (String lang : cl.getName())
            value += lang + ",";

        return value;
    }

}
