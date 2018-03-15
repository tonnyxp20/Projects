package com.developer.tonny.myroutine.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.developer.tonny.myroutine.R;
import com.developer.tonny.myroutine.WeightActivity;

public class DietActivity extends AppCompatActivity {

    private CardView cardView, cardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        setTitle("Dietas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardView = (CardView) findViewById(R.id.card1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receta = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(receta);
            }
        });

        cardView1 = (CardView) findViewById(R.id.card2);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WeightActivity.class);
                startActivity(intent);
            }
        });
    }
}
