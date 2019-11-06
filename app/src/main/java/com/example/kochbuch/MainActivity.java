package com.example.kochbuch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int SHOW_NOTE_REQUEST = 2;
    public static final int EDIT_NOTE_REQUEST = 3;

    private RecipeViewModel recipeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddRecipe = findViewById(R.id.button_add);
        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                adapter.setRecipe(recipes);
            }
        });

        adapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe) {
                Intent intent = new Intent(MainActivity.this, RecipeViewActivity.class);
                //Musste mit Extra gelösst werden, da es nicht aus der DB genommen werden kann von einer Aktiven Activity
                //"cannot access database on the main thread since it may potentially lock the UI for a long period of time."
                intent.putExtra(RecipeViewActivity.EXTRA_TITLE, recipe.getTitle());
                intent.putExtra(RecipeViewActivity.EXTRA_IMAGR, recipe.getImage());
                intent.putExtra("Recipe", recipe);
                startActivityForResult(intent, SHOW_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddRecipeActivity.EXTRA_TITLE); //Should never be null
            String description = data.getStringExtra(AddRecipeActivity.EXTRA_DESCRIPTION);
            int number = data.getIntExtra(AddRecipeActivity.EXTRA_NUMBER, 1);
            int difficulty = data.getIntExtra(AddRecipeActivity.EXTRA_DIFFICULTY, 1);
            String time = data.getStringExtra(AddRecipeActivity.EXTRA_TIME);
            byte[] picture = data.getByteArrayExtra(AddRecipeActivity.EXTRA_PICTURE);
            RecipeIngredients recipeIngredients = (RecipeIngredients) data.getSerializableExtra("RecipeIngredients");

            Recipe recipe = new Recipe(title, description, difficulty,time, picture, number, recipeIngredients); //TODO Picture Uploader
            recipeViewModel.insert(recipe);

            Toast.makeText(this, "Recipe saved", Toast.LENGTH_SHORT).show();
        } /*else if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {

            Recipe recipe = (Recipe) data.getSerializableExtra("Recipe");
            recipeViewModel.update(recipe);
            Toast.makeText(this, "Recipe updated", Toast.LENGTH_SHORT).show();

        } */else {
            Toast.makeText(this, "Recipe not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
