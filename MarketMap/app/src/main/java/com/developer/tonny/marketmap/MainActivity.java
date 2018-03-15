package com.developer.tonny.marketmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUser, edtPwd;
    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login");

        edtUser = (EditText) findViewById(R.id.idUser);
        edtPwd = (EditText) findViewById(R.id.idPwd);
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

    public void btnEntrarClick (View v ){
        String user = edtUser.getText().toString();
        String pwd = edtPwd.getText().toString();

        if(user.equals("us") && pwd.equals("123")){
            Intent i = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(i);
            finish();
            Toast.makeText(getApplicationContext(),
                    "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Acceso denegado!", Toast.LENGTH_SHORT).show();
        }

    }
}
