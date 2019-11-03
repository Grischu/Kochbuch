package com.example.kochbuch;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeViewActivity extends AppCompatActivity {

    private RecipeViewModel recipeViewModel;
    //private final IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
    private Recipe recipe;

    public static final String EXTRA_TITLE =
            "com.example.kochbuch.EXTRA_TITLE";

    public static final String EXTRA_IMAGR =
            "com.example.kochbuch.EXTRA_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);


        TextView textTitle = findViewById(R.id.recipeTitle);
        ImageView image = findViewById(R.id.recipeImage);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_TITLE)) {


            recipe = (Recipe) intent.getSerializableExtra("Recipe");

            textTitle.setText(recipe.getTitle());


            image.setImageResource(recipe.getImage());
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Recipe");


        RecyclerView recyclerView = findViewById(R.id.ingredients_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final IngredientsAdapter adapter = new IngredientsAdapter(recipe.getRecipeIngredients().getIngredientsList());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_recipe_menu, menu);
        menuInflater.inflate(R.menu.delete_recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_recipe:
                recipeViewModel.delete(recipe);
                Intent myIntent = new Intent(this, MainActivity.class);
                startActivity(myIntent);
                //TODO Intent extra toast "deleted"
                return true;
            case R.id.edit_recipe:
                Intent editIntent = new Intent(this, AddRecipeActivity.class);
                editIntent.putExtra("Recipe", recipe);
                startActivityForResult(editIntent, MainActivity.EDIT_NOTE_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3 && resultCode == RESULT_OK) {
            recipe = (Recipe) data.getSerializableExtra("Recipe");
            recipeViewModel.update(recipe);
            Toast.makeText(this, "Recipe updated", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, MainActivity.SHOW_NOTE_REQUEST);

        } else {
            Toast.makeText(this, "Recipe not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
