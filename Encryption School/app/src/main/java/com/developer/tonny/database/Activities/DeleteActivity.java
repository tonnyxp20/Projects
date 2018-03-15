package com.developer.tonny.database.Activities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.database.Conexion;
import com.developer.tonny.database.Encriptado;
import com.developer.tonny.database.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private Spinner spinner;
    private TextView txtNombres, txtApellidos, txtCarrera;
    private Button btnEliminar;

    List<String> lista = new ArrayList<String>();
    public String matricula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Baja alumno");
        setStrictMode();
        consultaMatricula();
        setSpinner();

        txtNombres = (TextView) findViewById(R.id.idNombres);
        txtApellidos = (TextView) findViewById(R.id.idApellidos);
        txtCarrera = (TextView) findViewById(R.id.idCarrera);
        btnEliminar = (Button) findViewById(R.id.idEliminar);
        btnEliminar.setEnabled(false);

    }

    public void eliminar(View view) {
        try {
            String respuesta = cn.post("http://tonnyxp20.x10host.com/eliminarEstudiante.php", encode.encriptar(matricula));

            txtNombres.setText("");
            txtApellidos.setText("");
            txtCarrera.setText("");

            spinner.setAdapter(null);
            setSpinner();

            showToast(respuesta);
            btnEliminar.setEnabled(false);

        } catch (Exception x) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar(View view) {
        btnEliminar.setEnabled(true);
        try {

            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarEstudiante.php", encode.encriptar(matricula)).split(",");

            txtNombres.setText(encode.desencriptar(respuesta[0]));
            txtApellidos.setText(encode.desencriptar(respuesta[1]) + " " + encode.desencriptar(respuesta[2]));
            txtCarrera.setText(encode.desencriptar(respuesta[5]));

        } catch (Exception x) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultaMatricula() {
        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarMatricula.php", "").split(",");

            int num = Integer.parseInt(respuesta[0]);

            lista.clear();

            if(num > 0) {
                for (int i = 0; i < num; i++) {
                    lista.add(encode.desencriptar(respuesta[i+1]));
                }
            } else {
                showToast("No hay datos.");
            }

        } catch (Exception e) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setSpinner() {
        spinner = (Spinner) findViewById(R.id.idSpinner);

        consultaMatricula();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matricula = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
