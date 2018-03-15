package com.developer.tonny.design.Activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.tonny.design.Fragments.FirstFragment;
import com.developer.tonny.design.R;

public class Emergencias extends AppCompatActivity {

    private EditText edtContacto;
    private Button btnGuardar;
    public String contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencias);

        setTitle("Emergencias");
        setToolbar();

        edtContacto = (EditText) findViewById(R.id.idContacto);
        btnGuardar = (Button) findViewById(R.id.idGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacto = edtContacto.getText().toString();
                Toast.makeText(getApplicationContext(), "Se ha guardado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
