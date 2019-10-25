package com.example.kochbuch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.kochbuch.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.kochbuch.EXTRA_DESCRIPTION";


    private EditText editTextTitle;
    private EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editTextTitle = findViewById(R.id.editRecipeTitle);
        editTextDescription = findViewById(R.id.editRecipeDescription);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Recipe");
    }

    private void saveRecipe() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        //Validate
        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);

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
