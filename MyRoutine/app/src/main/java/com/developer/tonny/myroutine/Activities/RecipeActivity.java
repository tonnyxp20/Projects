package com.developer.tonny.myroutine.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.developer.tonny.myroutine.R;

public class RecipeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        setTitle("Receta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
