package com.developer.tonny.marketmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CoordinatesActivity extends AppCompatActivity {

    private EditText edtNombre, edtLat, edtLng;
    public Button btnGuardar, btnLista, btnMapa;

    public static String[] name = new String[50];
    public static String[] lat = new String[50];
    public static String[] lng = new String[50];
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinates);

        setTitle("Coordenadas");

        edtNombre = (EditText) findViewById(R.id.idNombre);
        edtLat = (EditText) findViewById(R.id.idLat);
        edtLng = (EditText) findViewById(R.id.idLng);
        btnGuardar = (Button) findViewById(R.id.idGuardar);
        btnLista = (Button) findViewById(R.id.idLista);
        btnMapa = (Button) findViewById(R.id.idMapa);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                String latitud = edtLat.getText().toString();
                String longitud = edtLng.getText().toString();

                if (nombre.equals("") || latitud.equals("") || longitud.equals("")){
                    Toast.makeText(getApplicationContext(), "Complete los campos", Toast.LENGTH_SHORT).show();
                } else {
                    name[count] = nombre;
                    lat[count] = latitud;
                    lng[count] = longitud;

                    Toast.makeText(getApplicationContext(), "Registro existoso", Toast.LENGTH_LONG).show();

                    edtNombre.setText("");
                    edtLat.setText("");
                    edtLng.setText("");

                    count++;
                }
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(lista);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapa = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(mapa);
            }
        });
    }
}
