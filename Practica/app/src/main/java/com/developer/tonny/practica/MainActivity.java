package com.developer.tonny.practica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;
    EditText txt1, txt2, txtR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.idSuma);
        txt1 = (EditText)findViewById(R.id.idNum1);
        txt2 = (EditText)findViewById(R.id.idNum2);
        txtR = (EditText)findViewById(R.id.idResultado);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(), txt.getText().toString(), Toast.LENGTH_SHORT).show();
                int res = 0;
                int num1 = Integer.valueOf(txt1.getText().toString());
                int num2 = Integer.valueOf(txt2.getText().toString());

                res = num1 + num2;
                txtR.setText(res + "");
            }
        });

        btn2 = (Button)findViewById(R.id.idEnviar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent v2 = new Intent(getApplicationContext(), Ventana2.class);
                v2.putExtra("pack", "Hola Mundo");
                startActivity(v2);
            }
        });
    }
}
