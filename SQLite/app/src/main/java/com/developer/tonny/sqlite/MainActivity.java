package com.developer.tonny.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText txtCarrera, txtNombre, txtEdad;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        txtCarrera = (EditText) findViewById(R.id.idCarrera);
        txtNombre = (EditText) findViewById(R.id.idNombre);
        txtEdad = (EditText) findViewById(R.id.idEdad);
        btnGuardar = (Button) findViewById(R.id.idGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });
    }

    private void ClickMe() {
        String carrera = txtCarrera.getText().toString();
        String nombre = txtNombre.getText().toString();
        String edad = txtEdad.getText().toString();
        Boolean result = myDb.insertData(carrera, nombre, edad);
        if(result == true){
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error en Registro", Toast.LENGTH_SHORT).show();
        }
    }
}
