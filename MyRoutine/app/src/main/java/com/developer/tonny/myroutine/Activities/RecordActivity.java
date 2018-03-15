package com.developer.tonny.myroutine.Activities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.myroutine.Adapters.Encriptado;
import com.developer.tonny.myroutine.Conexion;
import com.developer.tonny.myroutine.R;

public class RecordActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private TextView txtCuenta, txtHistorial;
    public String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        setTitle("Historial");
        setStrictMode();

        txtCuenta = (TextView) findViewById(R.id.textViewCuenta);
        txtHistorial = (TextView) findViewById(R.id.textViewHistorial);

        nombre = getIntent().getStringExtra("name");
        txtCuenta.setText(nombre);

        consulta();
    }

    public void eliminar(View view) {

        try {
            String respuesta = cn.post("http://tonnyxp20.x10host.com/Podometro/eliminarHistorial.php", encode.shaEncryption(nombre));
            showToast(respuesta);

            consulta();

        } catch (Exception x) {
            Toast.makeText(this, "No hay datos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void consulta() {

        try {
            String respuesta = cn.post("http://tonnyxp20.x10host.com/Podometro/consultarHistorial.php", encode.shaEncryption(nombre));

            String[] dato = respuesta.split(",");
            int num = Integer.parseInt(dato[0]);
            int user = 4 * num;
            String datos = "";
            if(num > 0) {
                //  showToast(String.valueOf(dato.length));
                for (int i = 1; i < user; i++) {
                    datos += "Recorrido, Calorias, Tiempo y Pasos \n" + encode.desencriptar(dato[i]) + "\n";
                    //datos += encode.desencriptar(dato[i]);
                }
                //    showToast(datos);
                txtHistorial.setText(datos);
            } else {
                //txtHistorial.setText("");
                showToast("No hay datos.");
            }

            /*String[] dato = respuesta[0].split(",");
            int num = Integer.parseInt(dato[0]);

            String datos = "";
            if (num > 0) {
                for (int i = 1; i < num; i++) {
                    datos += "Recorrido, Calorias, Tiempo y Pasos \n" + respuesta[i].replace(",", ", ") + "\n\n";
                }
            } else {
                showToast("No hay datos.");
            }
            txtHistorial.setText(datos);
            */

        } catch (Exception x){
            Toast.makeText(this, "No hay datos.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();

        StrictMode.setThreadPolicy(policy);
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
