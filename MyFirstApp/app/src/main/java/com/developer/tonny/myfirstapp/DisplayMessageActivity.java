package com.developer.tonny.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intencion = getIntent();
        String mensaje = intencion.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView verTexto = new TextView(this);
        verTexto.setTextSize(40);
        verTexto.setText(mensaje);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(verTexto);
    }
}
