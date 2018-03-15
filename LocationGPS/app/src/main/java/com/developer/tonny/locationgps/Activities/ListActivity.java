package com.developer.tonny.locationgps.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.locationgps.R;

import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.cont;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.nombre;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.ubicacion;

public class ListActivity extends AppCompatActivity {

    private TextView txtNombre, txtUbicacion;
    private String name = "";
    private String pos = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setTitle("Lista");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNombre = (TextView) findViewById(R.id.idNombre);
        txtUbicacion = (TextView) findViewById(R.id.idUbicacion);

        if (cont != 0) {
            for (int i = 0; i < cont; i++) {
                name += nombre[i] + "\n";
                pos += ubicacion[i] + "\n";
            }
            txtNombre.setText(name);
            txtUbicacion.setText(pos);
        } else {
            Toast.makeText(getApplicationContext(), "No hay datos existentes", Toast.LENGTH_SHORT).show();
        }
    }

}
