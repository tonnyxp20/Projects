package com.developer.tonny.database.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.database.Conexion;
import com.developer.tonny.database.R;

public class ViewActivity extends AppCompatActivity {

    public Conexion cn = new Conexion();
    private TextView txtLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        setTitle("Ver usuarios");
        setStrictMode();

        txtLista = (TextView) findViewById(R.id.idLista);
        consultar();
    }

    private void consultar(){
        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarUsuario.php", "").split("&");

            String[] dato = respuesta[0].split(",");
            int num = Integer.parseInt(dato[0]);

            String datos = "";
            if(num > 0) {
                for (int i = 0; i < num; i++) {
                    datos += respuesta[i].replace(",", "\n") + "\n\n";
                }
            } else {
                showToast("No hay datos.");
            }

            txtLista.setText("Total de usuarios: " + datos);

        } catch (Exception e) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnBackClicked(View view) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        finish();
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
