package com.developer.tonny.myroutine.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.myroutine.Adapters.Encriptado;
import com.developer.tonny.myroutine.Conexion;
import com.developer.tonny.myroutine.DefaultActivity;
import com.developer.tonny.myroutine.R;

public class LoginActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private EditText edtUser, edtPwd;
    private Switch mySwitch;
    private TextView txtRegistrar;
    //public static String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        setStrictMode();
        setMySwitch();
        setRegistrar();

        edtUser = (EditText) findViewById(R.id.idUser);
        edtPwd = (EditText) findViewById(R.id.idPwd);

    }

    private void setMySwitch() {
        mySwitch = (Switch) findViewById(R.id.idMostrar);

        mySwitch.setChecked(false);
        mySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySwitch.isChecked()) {
                    mySwitch.setText("Ocultar");
                    edtPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mySwitch.setText("Mostrar");
                    edtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                edtPwd.setSelection(edtPwd.length());
            }
        });
    }

    private void setRegistrar() {
        txtRegistrar = (TextView) findViewById(R.id.idRegistro);
        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void consultar(View v) {
        String nombre = encode.shaEncryption(edtUser.getText().toString());
        String pwd = encode.shaEncryption(edtPwd.getText().toString());

        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/Podometro/consultaLogin.php", nombre).split(",");

            int num = Integer.parseInt(respuesta[0]);
            //String user = respuesta[2];
            String pass = respuesta[3];
            String tipo = encode.desencriptar(respuesta[4].replace("&", ""));

            if (num == 1) {
                if (pwd.equals(pass)) {
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
        if (tipo.equals("General")) {
            Intent i = new Intent(this, SplashActivity.class);
            i.putExtra("name", edtUser.getText().toString());
            startActivity(i);
        } else if (tipo.equals("Invitado")) {
            Intent i = new Intent(this, DefaultActivity.class);
            startActivity(i);
            finish();
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
