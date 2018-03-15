package com.developer.tonny.database;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegistryActivity extends AppCompatActivity {

    public Conexion cn = new Conexion();
    private EditText edtNombre, edtPass;
    public String tipo = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Registrar usuario");
        setStrictMode();

        edtNombre = (EditText) findViewById(R.id.idNombre);
        edtPass = (EditText) findViewById(R.id.idPass);
    }

    public void insertar(View v) {
        String nombre = edtNombre.getText().toString();
        String pass = edtPass.getText().toString();

        try {

            if (nombre.equals("") || pass.equals("")) {
                showToast("Complete los campos.");
            } else {
                String respuesta = cn.postUsuario("http://tonnyxp20.x10host.com/insertarUsuario.php", nombre, pass, tipo);
                showToast(respuesta);

                edtNombre.setText(""); edtPass.setText("");
            }

        } catch (Exception x) {
            showToast(x.toString());
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {

            case R.id.idAdmin:
                if (checked) {
                    tipo = "admin";
                }
                    break;

            case R.id.idGral:
                if (checked) {
                    tipo = "gral";
                }
                    break;
        }
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
