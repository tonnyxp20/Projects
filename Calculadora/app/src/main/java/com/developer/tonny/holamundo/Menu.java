package com.developer.tonny.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    Button btnMult, btnWS, btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setTitle("Menu");

        btnMult = (Button) findViewById(R.id.idMult);
        btnWS = (Button) findViewById(R.id.idWS);
        btnOut = (Button) findViewById(R.id.idOut);
    }

    // Método para abrir la tablas de multiplicar
    public void btnMultClick(View v){
        Intent tabla = new Intent(Menu.this, Tabla.class);
        startActivity(tabla);
    }

    // Método del launch para mostrar WhatsApp
    public void btnWSClick(View v){
        Intent launchWS = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        startActivity(launchWS);
    }

    // Método para regresar al Login
    public void btnOutClick(View v){
        Intent login = new Intent(Menu.this, MainActivity.class);
        startActivity(login);
        finish();
        Toast.makeText(getApplication(),
                "Se ha cerrado su sesión", Toast.LENGTH_SHORT).show();
    }

}
