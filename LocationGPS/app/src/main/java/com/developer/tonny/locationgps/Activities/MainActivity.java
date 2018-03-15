package com.developer.tonny.locationgps.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.locationgps.R;

import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.count;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.names;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.pass;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.types;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.user;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtPass;
    private Button btnEntrar;
    private TextView txtRegistrar;

    private boolean error = false;
    private String pwd;
    private String tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login");

        setTxtRegistrar();
        setBtnEntrar();
    }

    private void setTxtRegistrar() {
        txtRegistrar = (TextView) findViewById(R.id.idRegistro);
        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setBtnEntrar() {
        edtName = (EditText) findViewById(R.id.idName);
        edtPass = (EditText) findViewById(R.id.idPass);
        btnEntrar = (Button) findViewById(R.id.idEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edtName.getText().toString();
                pwd = edtPass.getText().toString();

                if (count != 0) {
                    for (int i = 0; i < count; i++) {
                        if (names[i].equals(user)) {
                            error = true;
                            Comprobar(i);
                        }
                    }
                }
                Validar();
                error = false;
            }
        });
    }

    public void Validar() {
        if (error == false){
            Toast.makeText(getApplicationContext(), "Acceso denegado", Toast.LENGTH_SHORT).show();
            edtName.setText("");
            edtPass.setText("");
        }
    }

    public void Comprobar(int i) {
        if(pass[i].equals(pwd)) {
            Toast.makeText(getApplicationContext(), "Bienvenido " + names[i], Toast.LENGTH_SHORT).show();
            tipo = types[i];
            Tipo(tipo);

            edtName.setText("");
            edtPass.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            edtPass.setText("");
        }
    }

    public void Tipo(String tipo) {
        if (tipo.equals("Administrador")) {
            Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
            startActivity(intent);
        } else if(tipo.equals("General")) {
            Intent i = new Intent(getApplicationContext(), GpsActivity.class);
            startActivity(i);
        }
    }
}
