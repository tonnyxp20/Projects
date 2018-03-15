package com.developer.tonny.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tabla extends AppCompatActivity {

    Button btnMult, btnCalc;
    EditText txtNum;
    TextView tvTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        // Activar feclas ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Tabla");

        tvTabla = (TextView) findViewById(R.id.idTabla);
        txtNum = (EditText) findViewById(R.id.idNum);
        btnMult = (Button) findViewById(R.id.idMult);
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String n = txtNum.getText().toString();
                    setTitle("Tabla del : " + n);

                    float n2 = Float.parseFloat(n);
                    String r2 = "";

                    for (int i = 1; i <= 10; i++){
                        if(n.contains(".") == false){
                            int res = i * (int) n2;
                            r2 += i + " x " + n + " = " + String.valueOf(res) + "\n";
                        } else {
                            float r = i * n2;
                            r2 += i + " x " + n + " = " + String.valueOf(r) + "\n";
                        }
                    }
                    tvTabla.setText(r2);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            "Ingrese un dato", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCalc = (Button) findViewById(R.id.idCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calc = new Intent(Tabla.this, Calculadora.class);
                startActivity(calc);
            }
        });
    }
}
