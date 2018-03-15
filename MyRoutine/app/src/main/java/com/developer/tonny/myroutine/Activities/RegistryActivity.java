package com.developer.tonny.myroutine.Activities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.tonny.myroutine.Adapters.Encriptado;
import com.developer.tonny.myroutine.Conexion;
import com.developer.tonny.myroutine.R;

import java.util.ArrayList;
import java.util.List;

public class RegistryActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private Spinner spinner;
    private EditText edtName, edtPassword;
    public String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Registrar usuario");
        setStrictMode();
        setSpinner();

        edtName = (EditText) findViewById(R.id.idUser);
        edtPassword = (EditText) findViewById(R.id.idPwd);

    }

    public void insertar(View v) {
        String nombre = encode.shaEncryption(edtName.getText().toString());
        String pass = encode.shaEncryption(edtPassword.getText().toString());

        try {

            if (nombre.equals("") || pass.equals("")) {
                showToast("Complete los campos.");
            } else {
                String respuesta = cn.postUsuario("http://tonnyxp20.x10host.com/Podometro/insertarUsuario.php", nombre, pass, encode.encriptar(tipo));
                showToast(respuesta);

                edtName.setText(""); edtPassword.setText("");
            }

        } catch (Exception x) {
            showToast(x.toString());
        }
    }

    public void setSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> tipos = new ArrayList<String>();
        tipos.add("General");
        tipos.add("Invitado");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();

        StrictMode.setThreadPolicy(policy);
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
