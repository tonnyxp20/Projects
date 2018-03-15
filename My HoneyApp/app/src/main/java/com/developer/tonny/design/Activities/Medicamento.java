package com.developer.tonny.design.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.tonny.design.R;

public class Medicamento extends AppCompatActivity {

    private EditText edtNombre;
    private Spinner spinner;
    private Button btnGuardar;

    public static String[] unidad = new String[25];
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        setToolbar();
        setTitle("Añadir medicamento");

        spinner = (Spinner) findViewById(R.id.idUnidad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.med_unidades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        guardarMedicamento();

    }

    // Agregamos el toolbar creado
    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void enviarDatos() {
        edtNombre = (EditText) findViewById(R.id.idNombre);
        String nombreMedicamento = edtNombre.getText().toString();

        unidad[count] = nombreMedicamento;
        count++;
    }

    private void guardarMedicamento() {
        btnGuardar = (Button) findViewById(R.id.idGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDatos();
                Toast.makeText(getApplicationContext(), "Se guardo el medicamento", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
