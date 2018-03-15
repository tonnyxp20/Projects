package com.developer.tonny.database.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.tonny.database.Conexion;
import com.developer.tonny.database.Encriptado;
import com.developer.tonny.database.R;

public class LoginActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private EditText edtUser, edtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        setStrictMode();

        edtUser = (EditText) findViewById(R.id.idUsuario);
        edtPwd = (EditText) findViewById(R.id.idPassword);
    }

    public void consultar(View v) {
        String user = encode.shaEncryption(edtUser.getText().toString());
        String pwd = encode.shaEncryption(edtPwd.getText().toString());

        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarLogin.php", user).split(",");

            int num = Integer.parseInt(respuesta[0]);
            //String nombre = respuesta[2];
            String pass = respuesta[3];
            String tipo = encode.desencriptar(respuesta[4].replace("&", ""));

            if (num == 1) {
                if (pwd.equals(pass)) {
                    //showToast("Bienvenid@ " + nombre);
                    comparar(tipo);
                    edtUser.setText(""); edtPwd.setText("");
                } else {
                    showToast("Contraseña incorrecta.");
                    edtPwd.setText("");
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void comparar(String tipo) {
        if (tipo.equals("admin")) {
            //showToast("Tipo " + tipo);
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
            finish();
        } else if (tipo.equals("gral")) {
            //showToast("Tipo " + tipo);
            Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void registrar(View v) {
        Intent i = new Intent(this, RegistryActivity.class);
        startActivity(i);
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
