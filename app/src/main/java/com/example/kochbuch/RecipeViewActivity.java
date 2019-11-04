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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecipeViewActivity extends AppCompatActivity {

    private RecipeViewModel recipeViewModel;
    //private final IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
    private Recipe recipe;

    public static final String EXTRA_TITLE =
            "com.example.kochbuch.EXTRA_TITLE";

    public static final String EXTRA_IMAGR =
            "com.example.kochbuch.EXTRA_IMAGE";

    int check = 0;
    Spinner dropDown;
    int temporaryNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        temporaryNumber = 0;

        TextView textTitle = findViewById(R.id.recipeTitle);
        TextView textDescription = findViewById(R.id.recipeDesc);
        ImageView image = findViewById(R.id.recipeImage);
        dropDown = findViewById(R.id.numberSpinner);
        Integer[] items = new Integer[]{1, 2, 3, 4, 5, 6};
        ArrayAdapter<Integer> dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropDown.setAdapter(dropDownAdapter);


        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        Intent intent = getIntent();

        if (intent.hasExtra("Recipe")) {


            recipe = (Recipe) intent.getSerializableExtra("Recipe");

            textTitle.setText(recipe.getTitle());
            textDescription.setText(recipe.getDescirption());


            image.setImageResource(recipe.getImage());
            dropDown.setSelection(getIndex(dropDown, recipe.getNumber()));
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Recipe");


        final RecyclerView recyclerView = findViewById(R.id.ingredients_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final IngredientsAdapter adapter = new IngredientsAdapter(recipe.getRecipeIngredients().getIngredientsList());
        recyclerView.setAdapter(adapter);

        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(++check > 1) {
                    List<Ingredients> ingredientsList = adapter.getName();
                    for(Ingredients ingredients : ingredientsList) {

                        double amount = ingredients.getAmount();
                        if(temporaryNumber == 0) {
                            temporaryNumber = recipe.getNumber();
                        }

                        amount = (amount / temporaryNumber) * (int) dropDown.getItemAtPosition(position);
                        ingredients.setAmount((int) Math.floor(amount));
                        //TODO Mit Double rechnen und abspeichern

                    }
                    temporaryNumber = (int) dropDown.getItemAtPosition(position);
                    adapter.setName(ingredientsList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

    private int getIndex(Spinner spinner, int number){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(number)){
                return i;
            }
        }

        return 0;
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

            Intent intent = new Intent(this, RecipeViewActivity.class);
            intent.putExtra("Recipe", recipe);
            startActivityForResult(intent, MainActivity.SHOW_NOTE_REQUEST);

            startActivity(intent);

        } else {
            Toast.makeText(this, "Recipe not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
