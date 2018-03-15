package com.developer.tonny.locationgps.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.tonny.locationgps.R;

import java.util.ArrayList;
import java.util.List;

import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.count;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.names;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.pass;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.types;

public class RegistryActivity extends AppCompatActivity {

    private EditText edtUser, edtPwd;
    private Spinner spinner;
    private Button btnGuardar;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        setTitle("Registro");
        setSpinner();
        setGuardar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> tipos = new ArrayList<String>();
        tipos.add("Administrador");
        tipos.add("General");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setGuardar() {
        edtUser = (EditText) findViewById(R.id.idUser);
        edtPwd = (EditText) findViewById(R.id.idPwd);
        btnGuardar = (Button) findViewById(R.id.idGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pwd = edtPwd.getText().toString();

                names[count] = user;
                pass[count] = pwd;
                types[count] = type;

                Toast.makeText(getApplicationContext(), "Registro existoso", Toast.LENGTH_LONG).show();

                edtUser.setText("");
                edtPwd.setText("");

                count++;
            }
        });
    }
}
