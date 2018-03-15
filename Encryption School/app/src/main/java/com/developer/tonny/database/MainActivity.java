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

                //String array[] = cn.post("http://tonnyxp20.x10host.com/select.php", "x").split(",");
                TextView txt = (TextView) findViewById(R.id.idTexto);


                String separar[] = x.split(",");
                String cantidad = separar[0];
                int cant = Integer.parseInt(cantidad);


                String fila[] = x.split("&"); // primera linea
                String info[] = fila[0].split(",");

                String dato = "";
                for (int i = 0; i < cant; i++) {
                    dato += fila[i] + "\n";
                }

                /*
                String info[] = fila[0].split(",");
                String id = "";
                for (int i = 0; i < cant; i++) {
                    id += info[i] + "\n";
                }
                */

                /*String dato = "";
                for (int i = 0; i < array.length; i+=4) {
                    dato += "id " + array[i+1] + "\n" +
                            "nombre " + array[i+2] + "\n" +
                            "contrasena " + array[i+3] + "\n" +
                            "tipo " + array[i+4] + "\n";
                }
*/
                txt.setText(dato);
            }
        });

    }

    public void consulta() {
        String array[] = cn.post("http://tonnyxp20.x10host.com/select.php", "x").split(",");
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
