package com.developer.tonny.database;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Conexion cn = new Conexion();
    private EditText edtDato;
    private Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();

        StrictMode.setThreadPolicy(policy);

        edtDato = (EditText) findViewById(R.id.idDato);
        btnConsultar = (Button) findViewById(R.id.idConsultar);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = cn.post("http://tonnyxp20.x10host.com/select.php", edtDato.getText().toString());
                TextView txt = (TextView) findViewById(R.id.idTexto);
                txt.setText(x);
            }
        });

    }

    public void ejecutar(View v) {
        String x = cn.post("http://tonnyxp20.x10host.com/prueba.php", "x");
        TextView txt = (TextView) findViewById(R.id.idTexto);
        txt.setText(x);
    }

    public void insert(View v){
        String x = cn.post("http://tonnyxp20.x10host.com/insert.php", edtDato.getText().toString());
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        edtDato.setText("");


        TextView txt = (TextView) findViewById(R.id.idTexto);
        txt.setText(x);
    }

}
