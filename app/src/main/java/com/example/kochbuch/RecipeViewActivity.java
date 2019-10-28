package com.example.kochbuch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeViewActivity extends AppCompatActivity {

    private RecipeViewModel recipeViewModel;

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

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_TITLE)) {

            textTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            int a = intent.getIntExtra(EXTRA_IMAGR, 0);

            recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

            image.setImageResource(a);
        }
    }
}
