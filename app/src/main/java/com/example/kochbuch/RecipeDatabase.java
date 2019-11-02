package com.example.kochbuch;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Recipe.class}, version = 4)
public abstract class RecipeDatabase extends RoomDatabase {

    private static RecipeDatabase instance;

    public abstract RecipeDao recipeDao();
    public static synchronized RecipeDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class,"recipe_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecipeDao recipeDao;

        private PopulateDbAsyncTask(RecipeDatabase db) {
            recipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids ) {
            List<String> ingredients = new ArrayList<String>();
            ingredients.add("asdf");
            ingredients.add("asdf2");

            Ingredients ingredients1 = new Ingredients(ingredients);
            //ingredients1.setName(ingredients);

            recipeDao.insert(new Recipe(
                    "Veganer Burger mit Karotten und Hummus",
                    "Test",
                    1,
                    1, //TODO Ingredients
                    R.drawable.testpicture, ingredients1));
            recipeDao.insert(new Recipe(
                    "Veganer Burger mit Karotten und Hummus",
                    "Test",
                    1,
                    1, //TODO Ingredients
                    R.drawable.testpicture, ingredients1));
            recipeDao.insert(new Recipe(
                    "Veganer Burger mit Karotten und Hummus",
                    "Test",
                    1,
                    1, //TODO Ingredients
                    R.drawable.testpicture, ingredients1));
            recipeDao.insert(new Recipe(
                    "Veganer Burger mit Karotten und Hummus",
                    "Test",
                    1,
                    1, //TODO Ingredients
                    R.drawable.testpicture, ingredients1));
            recipeDao.insert(new Recipe(
                    "Veganer Burger mit Karotten und Hummus",
                    "Test",
                    1,
                    1, //TODO Ingredients
                    R.drawable.testpicture, ingredients1));
            recipeDao.insert(new Recipe(
                    "Veganer Burger mit Karotten und Hummus",
                    "Test",
                    1,
                    1, //TODO Ingredients
                    R.drawable.testpicture, ingredients1));
            return null;
        }
    }

}
