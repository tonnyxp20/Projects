package com.developer.tonny.prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // R significa referencia
    }

    public void mensaje(View v){
        Toast.makeText(getApplicationContext(), "Hola Mundo", Toast.LENGTH_SHORT).show();
    }
}
