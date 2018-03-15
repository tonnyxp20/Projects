package com.developer.tonny.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends AppCompatActivity {

    TextView txtPantalla;
    Button btnBorrar, btnCero, btnPunto;
    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve;
    Button btnSuma, btnResta, btnMult, btnDiv, btnIgual;

    boolean secuencia = true;
    boolean punto = true;
    String cadena = "";
    String substring = "";
    String op = "";
    Double n1 = null;
    Double n2 = null;
    Double res = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        // Activar flechas ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Calculadora");

        txtPantalla = (TextView) findViewById(R.id.idPantalla);
        txtPantalla.setText("0.");

        btnBorrar = (Button)findViewById(R.id.idBorrar);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPantalla.setText("0.");
                secuencia = true;
                punto = true;
            }
        });

        btnPunto = (Button)findViewById(R.id.idPunto);
        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText("0.");
                    secuencia = false;
                }
                punto = false;
            }
        });

        btnCero = (Button)findViewById(R.id.id0);
        btnCero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("0.");
                    secuencia = false;
                } else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "0.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "0");
                        punto = false;
                    }
                }
            }
        });

        btnUno = (Button)findViewById(R.id.id1);
        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("1.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "1.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "1");
                        punto = false;
                    }
                }
            }
        });

        btnDos = (Button)findViewById(R.id.id2);
        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("2.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "2.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "2");
                        punto = false;
                    }
                }
            }
        });

        btnTres = (Button)findViewById(R.id.id3);
        btnTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("3.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "3.");
                    } else {
                        txtPantalla.setText(txtPantalla.getText() + "3");
                        punto = false;
                    }
                }
            }
        });

        btnCuatro = (Button)findViewById(R.id.id4);
        btnCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("4.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "4.");
                    } else {
                        txtPantalla.setText(txtPantalla.getText() + "4");
                        punto = false;
                    }
                }
            }
        });

        btnCinco = (Button)findViewById(R.id.id5);
        btnCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("5.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "5.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "5");
                        punto = false;
                    }
                }
            }
        });

        btnSeis = (Button)findViewById(R.id.id6);
        btnSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("6.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "6.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "6");
                        punto = false;
                    }
                }
            }
        });

        btnSiete = (Button)findViewById(R.id.id7);
        btnSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("7.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "7.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "7");
                        punto = false;
                    }
                }
            }
        });

        btnOcho = (Button)findViewById(R.id.id8);
        btnOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("8.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "8.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "8");
                        punto = false;
                    }
                }
            }
        });

        btnNueve = (Button)findViewById(R.id.id9);
        btnNueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secuencia == true){
                    txtPantalla.setText(null);
                    txtPantalla.setText("9.");
                    secuencia = false;
                }  else {
                    if (punto == true){
                        cadena = String.valueOf(txtPantalla.getText());
                        substring = cadena.substring(0, cadena.length() - 1);
                        txtPantalla.setText(substring + "9.");
                    }else {
                        txtPantalla.setText(txtPantalla.getText() + "9");
                        punto = false;
                    }
                }
            }
        });

        btnSuma = (Button)findViewById(R.id.idSuma);
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op = "+";
                n1 = Double.parseDouble(txtPantalla.getText().toString());
                secuencia = true;
                punto = true;
            }
        });

        btnResta = (Button)findViewById(R.id.idResta);
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op = "-";
                n1 = Double.parseDouble(txtPantalla.getText().toString());
                secuencia = true;
                punto = true;
            }
        });

        btnMult = (Button)findViewById(R.id.idMult);
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op = "*";
                n1 = Double.parseDouble(txtPantalla.getText().toString());
                secuencia = true;
                punto = true;
            }
        });

        btnDiv = (Button)findViewById(R.id.idDiv);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op = "/";
                n1 = Double.parseDouble(txtPantalla.getText().toString());
                secuencia = true;
                punto = true;
            }
        });

        btnIgual = (Button)findViewById(R.id.idIgual);
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    n2 = Double.parseDouble(txtPantalla.getText().toString());
                    punto = true;
                    secuencia = true;

                    switch (op) {
                        case "+":
                            res = n1 + n2;
                            break;

                        case "-":
                            res = n1 - n2;
                            break;

                        case "*":
                            res = n1 * n2;
                            break;

                        case "/":
                            res = n1 / n2;
                            break;
                    }

                    String r = res.toString();
                    //boolean aux = r.contains(".0");
                    if (r.contains(".0")) {
                        int num = res.intValue();
                        txtPantalla.setText(num + ".");
                    } else {
                        txtPantalla.setText(r);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplication(),
                            "Teclee un numero", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
