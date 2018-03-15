package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovow520.basededatos.Valores.VariablesGlobalesAlumnos;
import com.example.lenovow520.basededatos.Valores.VariablesGlobalesUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BajaAlumnos extends AppCompatActivity {

    Spinner spinnerMat = null;
    int i,id;
    TextView txtNom, txtApe;
    public Conexion cn = new Conexion();
    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baja_alumnos);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setTitle("Eliminar alumno");
        ObtenerDatos();
        spinner();

    }

    public void spinner(){
        spinnerMat = (Spinner) findViewById(R.id.spinnerBajaMatricula);
        ArrayList<String> Matricula = new ArrayList<>();
        Matricula.clear();
        for(i = 0; VariablesGlobalesAlumnos.contador>=i; i++)
        {
            Matricula.add(String.valueOf(VariablesGlobalesAlumnos.Matricula[i]));
        }
        if(i==0){
            showToast("No Hay Datos Registrados");
        }
        else {
            spinnerMat.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Matricula));
        }
        spinnerMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ObtenerInfo(spinnerMat.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void ObtenerDatos(){
        String respuesta = cn.post("http://tonnyxp20.x10host.com/Escuela/consultaalumnos.php", "");
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

    public void showToast(String msg){
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }
    public void ObtenerInfo(String matri){
        for( int l = 0; VariablesGlobalesAlumnos.contador>=l; l++)
        {
            if (matri.equals(String.valueOf(VariablesGlobalesAlumnos.Matricula[i])))
            {
                id=l;
              // modificarTxt();
            }
        }

    }

    public void modificarTxt(){
        txtApe = (TextView) findViewById(R.id.txtViewssApellidos);
        txtNom = (TextView) findViewById(R.id.textViewNombre);
        txtNom.setText(VariablesGlobalesAlumnos.Nombre[id]);
        txtApe.setText(VariablesGlobalesAlumnos.Paterno[id]+" "+VariablesGlobalesAlumnos.Materno[id]);
    }
    public void EliminarDato(View v) {
        String respuesta = cn.post("http://tonnyxp20.x10host.com/Escuela/deleteAlumnos.php", spinnerMat.getSelectedItem().toString());
        showToast(respuesta);
        ObtenerDatos();
        spinner();
    }
}
