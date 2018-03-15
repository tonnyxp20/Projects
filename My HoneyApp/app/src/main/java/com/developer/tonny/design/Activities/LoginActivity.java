package com.developer.tonny.design.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.tonny.design.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText edtUser, edtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        setToolbar();
        setEnter();

    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    private void setEnter() {
        edtUser = (EditText) findViewById(R.id.idUser);
        edtPwd = (EditText) findViewById(R.id.idPwd);
        btnEntrar = (Button) findViewById(R.id.idEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = edtUser.getText().toString();
                String pass = edtPwd.getText().toString();

                if (us.equals("us") && pass.equals("123")) {
                    Intent i = new Intent(getApplicationContext(), SplashActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Acceso denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
