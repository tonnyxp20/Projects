package com.developer.tonny.encryption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Encriptado ecryption = new Encriptado();
    private Button btnEncriptar;
    private EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = (EditText) findViewById(R.id.editTextTexto);
        btnEncriptar = (Button) findViewById(R.id.buttonEncriptar);

        btnEncriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtText.getText().toString();
                String dato = ecryption.encriptar(text);

                Intent v2 = new Intent(getApplicationContext(), ResultActivity.class);
                v2.putExtra("dato", dato);
                startActivity(v2);
            }
        });

    }

}
