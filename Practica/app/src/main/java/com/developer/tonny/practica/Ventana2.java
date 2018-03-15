package com.developer.tonny.practica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ventana2 extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);

        String info = getIntent().getExtras().getString("pack");
        txt = (TextView)findViewById(R.id.idMostrar);
        txt.setText(info);
    }
}
