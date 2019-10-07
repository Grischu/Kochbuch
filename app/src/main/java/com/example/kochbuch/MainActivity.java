package com.example.kochbuch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));
        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));
        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));
        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));
        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));
        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));
        recipeList.add(
                new Recipe(
                        1,
                        "Test",
                        "Test",
                        1,
                        1,
                        new ArrayList<String>(),
                        R.drawable.testpicture));


        adapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);


    }
}
