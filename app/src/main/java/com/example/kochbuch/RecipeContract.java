package com.example.kochbuch;

import android.provider.BaseColumns;

public class RecipeContract {

    private RecipeContract() {

    }

    public static final class RecipeEntry implements BaseColumns {
        public static final String TABLE_NAME = "recipeList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }


}
