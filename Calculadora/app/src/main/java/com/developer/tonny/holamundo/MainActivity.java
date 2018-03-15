package com.developer.tonny.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUs, txtPass;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login");

        txtUs = (EditText) findViewById(R.id.idUser);
        txtPass = (EditText) findViewById(R.id.idPwd);
        btnEntrar = (Button) findViewById(R.id.idEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUs.getText().toString();
                String pwd = txtPass.getText().toString();

                // Condición para loguearse e inciar el menu
                if(user.equals("admin") && pwd.equals("123")){
                    Intent menu = new Intent(MainActivity.this, Menu.class);
                    startActivity(menu);
                    finish();
                    Toast.makeText(getApplicationContext(),
                            "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Acceso denegado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para deshabilitar el retroceso
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
