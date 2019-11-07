package com.example.kochbuch.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kochbuch.Adapter.IngredientsAdapter;
import com.example.kochbuch.Model.Ingredients;
import com.example.kochbuch.Model.Recipe;
import com.example.kochbuch.Model.RecipeIngredients;
import com.example.kochbuch.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.kochbuch.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.kochbuch.EXTRA_DESCRIPTION";

    public static final String EXTRA_NUMBER =
            "com.example.kochbuch.NUMBER";

    public static final String EXTRA_DIFFICULTY =
            "com.example.kochbuch.DIFFICULTY";

    public static final String EXTRA_TIME =
            "com.example.kochbuch.EXTRA_TIME";

    public static final String EXTRA_PICTURE = "com.example.kochbuch.PICTURE";

    public static final int OPEN_CAMERA = 55;

    private byte [] imageByte;

    private EditText editTextTitle;
    private EditText editTextDescription;
    RecyclerView recyclerView;
    private Spinner numberSpinner;
    private Spinner difficultySpinner;
    private ImageView addRecipeImage;
    private EditText editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recyclerView = findViewById(R.id.ingredients_recycler_view);
        editTextTitle = findViewById(R.id.editRecipeTitle);
        editTextDescription = findViewById(R.id.editRecipeDescription);
        numberSpinner = findViewById(R.id.numberSpinner);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        addRecipeImage = findViewById(R.id.addRecipeImage);
        editTextTime = findViewById(R.id.timeText);

        addRecipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(intent, "select a picture"), OPEN_CAMERA);
                }
            }
        });

        Integer[] items = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        numberSpinner.setAdapter(dropDownAdapter);
        difficultySpinner.setAdapter(dropDownAdapter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra("Recipe")) {
            Recipe recipe = (Recipe) intent.getSerializableExtra("Recipe");
            editTextTitle.setText(recipe.getTitle());
            editTextDescription.setText(recipe.getDescirption());
            numberSpinner.setSelection(getIndex(numberSpinner, recipe.getNumber()));
            difficultySpinner.setSelection(getIndex(difficultySpinner, recipe.getDifficulty()));
            editTextTime.setText(recipe.getTime());


            byte[] imageByte = recipe.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            addRecipeImage.setImageBitmap(bmp);

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
        int number = (Integer) numberSpinner.getSelectedItem();
        int difficulty = (Integer) difficultySpinner.getSelectedItem();
        String time = editTextTime.getText().toString();
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
        if(time.isEmpty()) {
            Toast.makeText(this, "Please insert a time", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ingredients.size() == 0) {
            Toast.makeText(this, "Please insert ingredients", Toast.LENGTH_SHORT).show();
            return;
        }
        for(Ingredients ingredientsObj : ingredients) {
            if(ingredientsObj.getName().trim().isEmpty() || ingredientsObj.getUnit().trim().isEmpty()) {
                Toast.makeText(this, "Please insert values for all ingrediences", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if(imageByte == null &! getIntent().hasExtra("Recipe")) {
            Toast.makeText(this, "Please insert a image", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        if(getIntent().hasExtra("Recipe")) {
            Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");
            recipe.setTitle(title);
            recipe.setDescirption(description);
            recipe.setNumber(number);
            recipe.setDifficulty(difficulty);
            if(imageByte != null) {
                recipe.setImage(imageByte);
            }
            recipe.setTime(time);

            data.putExtra("Recipe", recipe);
        }

        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_NUMBER, number);
        data.putExtra(EXTRA_DIFFICULTY, difficulty);
        data.putExtra(EXTRA_TIME, time);
        data.putExtra(EXTRA_PICTURE, imageByte);
        data.putExtra("RecipeIngredients", recipeIngredients);

        setResult(RESULT_OK, data);
        finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAMERA) {
            if(resultCode == RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageByte = stream.toByteArray();
                addRecipeImage.setImageBitmap(bmp);
            }
        }
    }

}
