package com.developer.tonny.encryption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    public Encriptado encryption = new Encriptado();
    private TextView txtResultado, txtDesencriptar;
    public String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResultado = (TextView) findViewById(R.id.textView);
        txtDesencriptar = (TextView) findViewById(R.id.Desencriptado);

        result = getIntent().getStringExtra("dato");
        txtResultado.setText(result);
    }

    public void onBackClicked(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onDecryptClicked(View view) {
        String dato = encryption.desencriptar(result);
        txtDesencriptar.setText(dato);
    }

}
