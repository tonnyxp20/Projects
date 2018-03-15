package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovow520.basededatos.Valores.VariablesGlobalesAlumnos;
import com.example.lenovow520.basededatos.Valores.VariablesGlobalesUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReportesAlumnos extends AppCompatActivity {

    Spinner spinnerMat = null;
    Spinner spinnerCarre = null;
    Spinner spinnerEdad = null;
    TextView txtMostrar = null;
    int contMat;
    public Conexion cn = new Conexion();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_alumnos);

        setTitle("Reportes");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ObtenerDatos();
        txtMostrar = (TextView) findViewById(R.id.textViewReporte);
        crearSpinners();
        spinnerCarre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CargarTextCarre(spinnerCarre.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CargarTextMatri(spinnerMat.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CargarTextEdad(spinnerEdad.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean marcado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonMatricula:
                if (marcado) {
                    VisibleMatricula();
                }
                break;

            case R.id.radioButtonCarrera:
                if (marcado) {
                    VisibleCarrera();
                }
                break;
            case R.id.radioButtonEdad:
                if (marcado) {
                    VisibleEdad();
                }
                break;
        }
    }

    public void crearSpinners() {
        spinnerMat = (Spinner) findViewById(R.id.spinnerRepoCarrera);
        spinnerCarre = (Spinner) findViewById(R.id.spinnerMatricula);
        spinnerEdad = (Spinner) findViewById(R.id.spinnerEdad);

        final ArrayList<String> Carrera = new ArrayList<>();
        final ArrayList<String> Matricula = new ArrayList<>();
        final ArrayList<String> Edad = new ArrayList<>();

        for (contMat = 0; VariablesGlobalesAlumnos.contador >= contMat; contMat++) {
            Matricula.add(String.valueOf(VariablesGlobalesAlumnos.Matricula[contMat]));
            Carrera.add(String.valueOf(VariablesGlobalesAlumnos.Carrera[contMat]));
            Edad.add(String.valueOf(VariablesGlobalesAlumnos.Edad[contMat]));
        }
        if (contMat == 0) {
            showToast("No Hay Datos");
        } else {
            spinnerMat.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Matricula));
            spinnerCarre.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Carrera));
            spinnerEdad.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Edad));
            spinnerEdad.setSelection(0);
            spinnerCarre.setSelection(0);
            spinnerMat.setSelection(0);
        }
        VisibleMatricula();
    }

    public void VisibleMatricula() {
        spinnerMat.setVisibility(View.VISIBLE);
        spinnerCarre.setVisibility(View.INVISIBLE);
        spinnerEdad.setVisibility(View.INVISIBLE);

    }

    public void VisibleCarrera() {
        spinnerMat.setVisibility(View.INVISIBLE);
        spinnerCarre.setVisibility(View.VISIBLE);
        spinnerEdad.setVisibility(View.INVISIBLE);

    }

    public void VisibleEdad() {
        spinnerMat.setVisibility(View.INVISIBLE);
        spinnerCarre.setVisibility(View.INVISIBLE);
        spinnerEdad.setVisibility(View.VISIBLE);
    }

    public void showToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    public void CargarTextMatri(String v) {
        int id = 0;
        String Mostrar;
        for (int i = 0; VariablesGlobalesUser.contador >= i; i++) {
            if (v.equals(String.valueOf(VariablesGlobalesAlumnos.Matricula[i]))) {
                id = i;
            }
        }
        Mostrar = "Matrcula: " + VariablesGlobalesAlumnos.Matricula[id] +
                "Nombre: " + VariablesGlobalesAlumnos.Nombre[id]
                + " " + VariablesGlobalesAlumnos.Paterno[id] +
                " " + VariablesGlobalesAlumnos.Materno[id] +
                "Nacimiento: " + VariablesGlobalesAlumnos.Dia[id] + "/" + VariablesGlobalesAlumnos.Mes[id] + "/" + VariablesGlobalesAlumnos.Ano[id] +
                "Edad: " + VariablesGlobalesAlumnos.Edad[id] +
                "Carrera" + VariablesGlobalesAlumnos.Carrera[id];

        txtMostrar.setText(Mostrar);
    }

    public void CargarTextCarre(String v) {
        int id = 0;
        String Mostrar;
        for (int i = 0; VariablesGlobalesUser.contador >= i; i++) {
            if (v.equals(String.valueOf(VariablesGlobalesAlumnos.Carrera[i]))) {
                id = i;
            }
        }
        Mostrar = "Matrcula: " + VariablesGlobalesAlumnos.Matricula[id] +
                "Nombre: " + VariablesGlobalesAlumnos.Nombre[id]
                + " " + VariablesGlobalesAlumnos.Paterno[id] +
                " " + VariablesGlobalesAlumnos.Materno[id] +
                "Nacimiento: " + VariablesGlobalesAlumnos.Dia[id] + "/" + VariablesGlobalesAlumnos.Mes[id] + "/" + VariablesGlobalesAlumnos.Ano[id] +
                "Edad: " + VariablesGlobalesAlumnos.Edad[id] +
                "Carrera" + VariablesGlobalesAlumnos.Carrera[id];

        txtMostrar.setText(Mostrar);

    }

    public void CargarTextEdad(String v) {
        int id = 0;
        String Mostrar;
        for (int i = 0; VariablesGlobalesUser.contador >= i; i++) {
            if (v.equals(String.valueOf(VariablesGlobalesAlumnos.Edad[i]))) {
                id = i;
            }
        }
        Mostrar = "Matrcula: " + VariablesGlobalesAlumnos.Matricula[id] +
                "Nombre: " + VariablesGlobalesAlumnos.Nombre[id]
                + " " + VariablesGlobalesAlumnos.Paterno[id] +
                " " + VariablesGlobalesAlumnos.Materno[id] +
                "Nacimiento: " + VariablesGlobalesAlumnos.Dia[id] + "/" + VariablesGlobalesAlumnos.Mes[id] + "/" + VariablesGlobalesAlumnos.Ano[id] +
                "Edad: " + VariablesGlobalesAlumnos.Edad[id] +
                "Carrera" + VariablesGlobalesAlumnos.Carrera[id];

        txtMostrar.setText(Mostrar);

    }
    public void ObtenerDatos(){
        String respuesta = cn.post("http://atime.x10host.com/consultaalumnos.php", "");
        try{
            JSONArray jsonarray = new JSONArray(respuesta);
            for(int i=0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                VariablesGlobalesAlumnos.Matricula[i] = jsonobject.getInt("Matricula");
                VariablesGlobalesAlumnos.Nombre[i] = jsonobject.getString("Nombre");
                VariablesGlobalesAlumnos.Paterno[i]= jsonobject.getString("Paterno");
                VariablesGlobalesAlumnos.Materno[i]= jsonobject.getString("Materno");
                VariablesGlobalesAlumnos.Dia[i]= jsonobject.getInt("Dia");
                VariablesGlobalesAlumnos.Mes[i]= jsonobject.getInt("Mes");
                VariablesGlobalesAlumnos.Ano[i]= jsonobject.getInt("Ano");
                VariablesGlobalesAlumnos.Edad[i]= jsonobject.getInt("Edad");
                VariablesGlobalesAlumnos.Carrera[i]= jsonobject.getString("Carrera");
                VariablesGlobalesAlumnos.contador=i;
            }
        }catch (JSONException e){
            showToast(e.toString());
        }
    }
}
