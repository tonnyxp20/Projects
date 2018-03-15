package com.developer.tonny.myroutine.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.tonny.myroutine.Adapters.AdapterCategory;
import com.developer.tonny.myroutine.Adapters.Category;
import com.developer.tonny.myroutine.R;

import java.util.ArrayList;

public class FitnessActivity extends AppCompatActivity {

    ArrayList<Category> category = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        setTitle("Ejercicios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        category.add(new Category("1",
                "Abdominales",
                "Tonifica y marca tus abdominales con los mejores ejercicios en casa, desde lo más básicos para principiantes hasta los más avanzados para machacar el abdomen."
                , R.drawable.abdomen));

        category.add(new Category("2",
                "Piernas",
                "Gana fuerza, potencia y masa muscular en las piernas con los mejores ejercicios en casa."
                , R.drawable.pierna));

        category.add(new Category("3",
                "Tonoficar Glúteos",
                "Los mejores ejercicios en casa para todas las chicas que buscan tonificar sus glúteos y conseguir un trasero perfecto."
                , R.drawable.gluteos));

        category.add(new Category("4",
                "Brazos",
                "Descubre los mejores ejercicios en casa para trabajar los brazos, incluyendo ejercicios para ganar masa muscular en los bíceps, tríceps, hombros y antebrazos."
                , R.drawable.brazo));

        category.add(new Category("5",
                "Espalda",
                "El remo renegado es un ejercicio que nos ayudará a trabajar la espalda, en concreto las dorsales usando un par de mancuerna."
                , R.drawable.espalda));

        ListView lv = (ListView) findViewById(R.id.listview);
        final AdapterCategory adapter = new AdapterCategory(this, category);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                //CODIGO AQUI
                String text = String.valueOf(pos);
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Intent video = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(video);
            }
        });
    }
}
