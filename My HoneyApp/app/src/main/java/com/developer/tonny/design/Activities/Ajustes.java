package com.developer.tonny.design.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.developer.tonny.design.R;

public class Ajustes extends AppCompatActivity {

    private ListView listView;
    private String[] datos = {"Preguntas frecuentes", "Contáctanos", "Términos y Privacidad", "Info. de la app"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        setTitle("Ajustes");
        setToolbar();

        listView = (ListView) findViewById(R.id.idListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listView.setAdapter(adapter);
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
