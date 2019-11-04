package com.example.kochbuch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.kochbuch.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.kochbuch.EXTRA_DESCRIPTION";


    private EditText editTextTitle;
    private EditText editTextDescription;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recyclerView = findViewById(R.id.ingredients_recycler_view);
        editTextTitle = findViewById(R.id.editRecipeTitle);
        editTextDescription = findViewById(R.id.editRecipeDescription);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra("Recipe")) {
            Recipe recipe = (Recipe) intent.getSerializableExtra("Recipe");
            editTextTitle.setText(recipe.getTitle());
            editTextDescription.setText(recipe.getDescirption());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            final IngredientsAdapter adapter = new IngredientsAdapter(recipe.getRecipeIngredients().getIngredientsList());
            recyclerView.setAdapter(adapter);

            setTitle("Edit Recipe");
        } else {
            List<Ingredients> ingredients = new ArrayList<>();
            final IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            setTitle("Add Recipe");
        }


        FloatingActionButton addIngredientsButton = findViewById(R.id.addIngredientsButton);
        addIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientsAdapter adapter = (IngredientsAdapter) recyclerView.getAdapter();
                if(adapter != null) {
                    adapter.addData();
                }
            }
        });

    }

    private void saveRecipe() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        IngredientsAdapter adapter = (IngredientsAdapter) recyclerView.getAdapter();
        List<Ingredients> ingredients = new ArrayList<>();
        if(adapter != null) {
            ingredients = adapter.getName();
        }

        RecipeIngredients recipeIngredients = new RecipeIngredients(ingredients);


        //Validate
        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        if(getIntent().hasExtra("Recipe")) {
            Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");
            recipe.setTitle(title);
            recipe.setDescirption(description);
            data.putExtra("Recipe", recipe);
        }

        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra("RecipeIngredients", recipeIngredients);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_recipe:
                saveRecipe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
