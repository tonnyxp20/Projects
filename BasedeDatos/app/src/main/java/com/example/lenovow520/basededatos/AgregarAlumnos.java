package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class AgregarAlumnos extends AppCompatActivity {
    int Edad;
    int ANO=2017;
    boolean us = false;
   boolean mt=false;
    EditText txtNom, txtApeP, txtApeM, txtMat;
    TextView txtEdad=null;
    Spinner spinnerDia = null;
    Spinner spinnerMes = null;
    Spinner spinnerAno = null;
    Spinner spinnerCarrera = null;
    public Conexion cn = new Conexion();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumnos);

        setTitle("Agregar alumno");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ObtenerDatos();
        txtEdad=(TextView) findViewById(R.id.txtVEdad);

        crearSpinners();

        spinnerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CalcularEdad(Integer.parseInt(spinnerAno.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void agregaraBasedeDatos (String Matricula, String Nombre, String Paterno, String Materno, String Dia, String Mes, String Ano, String Edad, String Carrera){
        String respuesta = cn.AgregarAlumno("http://tonnyxp20.x10host.com/Escuela/InsertarAlumno.php", Matricula,Nombre,Paterno, Materno, Dia, Mes, Ano, Edad, Carrera);
        showToast(respuesta);
        ObtenerDatos();
    }

    public void CalcularEdad(int edad){
        Edad = ANO-edad;
        txtEdad.setText(String.valueOf(Edad));
    }

    public void crearSpinners(){
        spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
        spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
        spinnerAno = (Spinner) findViewById(R.id.spinnerAno);
        spinnerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);

        final ArrayList<String> dias = new ArrayList<>();
        final ArrayList<String> mes = new ArrayList<>();
        final ArrayList<String> ano = new ArrayList<>();
        final ArrayList<String> Carre = new ArrayList<>();

        Carre.add("Sistemas");
        Carre.add("Terapia");
        Carre.add("Finanzas");
        Carre.add("Nanotecnologia");
        Carre.add("Industrial");
        Carre.add("Contabilidad");

        for(int i=1; i<32;i++)
        {
            dias.add(String.valueOf(i));
        }

        for(int i=1; i<13;i++)
        {
            mes.add(String.valueOf(i));
        }
        for(int i=1980; i<2005;i++)
        {
            ano.add(String.valueOf(i));
        }

        spinnerDia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dias));
        spinnerMes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mes));
        spinnerAno.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ano));
        spinnerCarrera.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Carre));
        spinnerDia.setSelection(0);
        spinnerMes.setSelection(0);
        spinnerAno.setSelection(0);


    }
    public void obtenerValores (View v){

        txtMat = (EditText) findViewById(R.id.txtMatricula);
        txtNom = (EditText) findViewById(R.id.txtNombre);
        txtApeP = (EditText) findViewById(R.id.txtApellido);
        txtApeM = (EditText) findViewById(R.id.txtMaterno);

        String Mat = txtMat.getText().toString();
        String Nom = txtNom.getText().toString();
        String ApeP = txtApeP.getText().toString();
        String ApeM = txtApeM.getText().toString();

        String dias=spinnerDia.getSelectedItem().toString();
        String mes=spinnerMes.getSelectedItem().toString();
        String anos=spinnerAno.getSelectedItem().toString();
        String Carre=spinnerCarrera.getSelectedItem().toString();

        String ed = String.valueOf(Edad);

        for(int i = 0; VariablesGlobalesUser.contador>=i; i++)
        {
            if (Mat.equals(String.valueOf(VariablesGlobalesAlumnos.Matricula[i]))){
                mt = true;
            }
            else if(Nom.equals(VariablesGlobalesAlumnos.Nombre[i]))
            {
                us=true;
            }
        }
        if (mt==false){

            if(us==true){
                showToast("El nombre ya existe");
                us=false;
            }
            else {
                agregaraBasedeDatos(Mat,Nom,ApeP, ApeM, dias, mes,anos, ed, Carre);
            }
        }
        else{
            showToast("La Matricula Ya existe");
            mt = false;
        }
        txtNom.setText("");
        txtMat.setText("");
        txtApeP.setText("");
        txtApeM.setText("");
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
}
