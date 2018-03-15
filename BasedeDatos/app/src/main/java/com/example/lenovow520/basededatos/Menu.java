package com.example.lenovow520.basededatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setTitle("Menu");
    }


    public void AbrirAlta (View v){
        Intent Ver = new Intent(getApplicationContext(), AgregarAlumnos.class);
        startActivity(Ver);

    }


    public void AbrirBaja (View v){
        Intent Ver = new Intent(getApplicationContext(), BajaAlumnos.class);
        startActivity(Ver);
    }


    public void AbrirCambio (View v){
        Intent Ver = new Intent(getApplicationContext(), CambiarAlumnos.class);
        startActivity(Ver);
    }


    public void AbrirRegistrar (View v){
        Intent Ver = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(Ver);
    }


    public void AbrirLogout (View v){
        Intent Ver = new Intent(getApplicationContext(), Login.class);
        startActivity(Ver);
        finish();
    }


    public void AbrirVer (View v){
        Intent Ver = new Intent(getApplicationContext(), Ver.class);
        startActivity(Ver);
    }
}
