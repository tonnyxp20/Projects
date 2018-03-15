package com.developer.tonny.database.Activities;

import android.os.StrictMode;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.database.Conexion;
import com.developer.tonny.database.Encriptado;
import com.developer.tonny.database.R;

import org.w3c.dom.Text;

import java.io.ObjectStreamConstants;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private Button btnBuscar;
    private TextView txtDatos;
    private EditText edtMatricula;
    private Spinner spinner;
    public String posicion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        setTitle("Reportes");
        setStrictMode();

        btnBuscar = (Button) findViewById(R.id.buttonBuscar);
        txtDatos = (TextView) findViewById(R.id.textViewDatos);
        edtMatricula = (EditText) findViewById(R.id.editTextMatricula);
        edtMatricula.setEnabled(false);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setEnabled(false);
    }

    public void consultarEdad() {
        txtDatos.setText("");
        try {
            String respuesta = cn.post("http://tonnyxp20.x10host.com/consultarEdad.php", encode.encriptar(posicion));
            //showToast(respuesta);
            String[] dato = respuesta.split(",");
            int num = Integer.parseInt(dato[0]);
            int user = 6 * num;
            String datos = "";
            if(num > 0) {
                //  showToast(String.valueOf(dato.length));
                for (int i = 0; i < user; i++) {
                    //datos += respuesta[i];
                    datos += encode.desencriptar(dato[i+1]) + "\n";
                }
                //    showToast(datos);
                txtDatos.setText(datos);
            } else {
                txtDatos.setText("");
                showToast("No hay datos.");
            }

            /*String[] dato = respuesta[0].split(",");
            int num = Integer.parseInt(dato[0]);

            String datos = "";
            if(num > 0) {
                for (int i = 0; i < num; i++) {
                    datos += respuesta[i].replace(",", "\n") + "\n\n";
                }
                txtDatos.setText("Total de usuarios: " +  datos);
            } else {
                txtDatos.setText("");
                showToast("No hay datos.");
            }*/
        } catch (Exception x) {
            //Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
            showToast(x.toString());
        }
    }

    public void consultarCarrera() {
        txtDatos.setText("");
        try {
            String respuesta = cn.post("http://tonnyxp20.x10host.com/consultarCarrera.php", encode.encriptar(posicion));
            //showToast(respuesta);
            String[] dato = respuesta.split(",");
            int num = Integer.parseInt(dato[0]);
            int user = 6 * num;
            String datos = "";
            if(num > 0) {
              //  showToast(String.valueOf(dato.length));
                    for (int i = 0; i <= user; i++) {
                        //datos += respuesta[i];
                        datos += encode.desencriptar(dato[i+1]) + "\n";
                    }
                //    showToast(datos);
                txtDatos.setText(datos);
            } else {
                txtDatos.setText("");
                showToast("No hay datos.");
            }

            /*String[] dato = respuesta[0].split(",");
            int num = Integer.parseInt(dato[0]);

            String datos = "";
            if(num > 0) {
                for (int i = 0; i < num; i++) {
                    datos += respuesta[i].replace(",", "\n") + "\n\n";
                }
                txtDatos.setText("Total de usuarios: " +  datos);
            } else {
                txtDatos.setText("");
                showToast("No hay datos.");
            }*/

        } catch (Exception x) {
            //Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
            //showToast(x.toString());
        }
    }

    public void consultarMatricula() {
        String matricula = encode.encriptar(edtMatricula.getText().toString());
        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarEstudiante.php", matricula).split(",");

            String nombre = encode.desencriptar(respuesta[0]);
            String paterno = encode.desencriptar(respuesta[1]);
            String materno = encode.desencriptar(respuesta[2]);
            String fecha = encode.desencriptar(respuesta[3]);
            String edad = encode.desencriptar(respuesta[4]);
            String carrera = encode.desencriptar(respuesta[5]);

            txtDatos.setText("Nombre: " + nombre + " " + paterno + " " + materno +
                                "\n" + "Fecha de nacimiento: " + fecha +
                                "\n" + "Edad: " + edad +
                                "\n" + "Carrera: " + carrera);

        } catch (Exception x) {
            Toast.makeText(this, "No hay datos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void spinnerCarrera() {
        List<String> tipos = new ArrayList<String>();
        tipos.add("Sistemas");
        tipos.add("Redes");
        tipos.add("Contabilidad");
        tipos.add("Terapia");
        tipos.add("Mecatronica");

        setAdapter(tipos);
    }

    public void spinnerEdad() {
        List<String> edades = new ArrayList<String>();
        int maxEdad = 40;
        for (int i = 17; i <= maxEdad; i++) {
            edades.add(String.valueOf(i));
        }

        setAdapter(edades);
    }

    public void setAdapter(List lista) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {

            case R.id.radioButtonMatricula:
                if (checked) {
                    txtDatos.setText("");
                    edtMatricula.setEnabled(true);
                    edtMatricula.setText("");
                    spinner.setEnabled(false);
                    btnBuscar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            consultarMatricula();
                        }
                    });
                }
                break;

            case R.id.radioButtonCarrera:
                if (checked) {
                    txtDatos.setText("");
                    spinnerCarrera();
                    edtMatricula.setEnabled(false);
                    edtMatricula.setText("");
                    spinner.setEnabled(true);
                    btnBuscar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            consultarCarrera();
                        }
                    });
                }
                break;

            case R.id.radioButtonEdad:
                if (checked) {
                    txtDatos.setText("");
                    spinnerEdad();
                    edtMatricula.setEnabled(false);
                    edtMatricula.setText("");
                    spinner.setEnabled(true);
                    btnBuscar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            consultarEdad();
                        }
                    });
                }
                break;
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
