package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

public class CambiarAlumnos extends AppCompatActivity {
    private DatePicker datePicker;
    private TextView dateView, txtEdad, txtMat, txtApeP, txtApeM, txtNom;
    private Button btnChangeDate;
    private int ano, mes, dia, Edad, id;
    int ANO=2017;
    private boolean us=false;
    Spinner spinnerCarrera = null;
    static final int DATE_DIALOG_ID = 999;
    public Conexion cn = new Conexion();
    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_alumnos);
        txtEdad = (TextView) findViewById(R.id.txtedad);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ObtenerDatos();
        setCurrentDateOnView();
        addListenerOnButton();
        spinnerCarrera = (Spinner) findViewById(R.id.spinnerCambiarCarrera);
        ArrayList<String> Carrera = new ArrayList<>();
        Carrera.add("Tics");
        Carrera.add("Terapia");
        Carrera.add("Finanzas");
        Carrera.add("NanoTecnologia");
        Carrera.add("Industrial");

        spinnerCarrera.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Carrera));

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setCurrentDateOnView() {

        dateView = (TextView) findViewById(R.id.txtFecha);
        datePicker = (DatePicker) findViewById(R.id.dpResult);
        final Calendar c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        dateView.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mes+1).append("/").append(dia).append("/")
                .append(ano).append(" "));

        // set current date into datepicker
        datePicker.init(ano, mes, dia, null);

    }
    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnFecha);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }
    public void CalcularEdad(int edad){
        Edad = ANO-edad;
        txtEdad.setText("Edad "+String.valueOf(Edad));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        ano, mes,dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            ano = selectedYear;
            mes = selectedMonth;
            dia = selectedDay;

            // set selected date into textview
            dateView.setText(new StringBuilder().append(mes)
                    .append("/").append(dia).append("/").append(ano)
                    .append(" "));

            // set selected date into datepicker also
            datePicker.init(ano, mes, dia, null);
            CalcularEdad(ano);

        }
    };
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

    public void BuscarMatricula(View v){
        txtMat = (EditText) findViewById(R.id.EtxtMatricula);
        String Matricula=txtMat.getText().toString();
        for(int i = 0; VariablesGlobalesUser.contador>=i; i++)
        {
            if (Matricula.equals(String.valueOf(VariablesGlobalesAlumnos.Matricula[i]))){
                id=i;
                us = true;
            }
        }
        if (us==false){
            showToast("Matricula no encontrada");
        }
        else{
            rellenarCampos();
            us = false;
        }
    }

    public void rellenarCampos(){
        txtApeP = (EditText) findViewById(R.id.EtxtApePa);
        txtApeM =(EditText) findViewById(R.id.EtxtApeMa);
        txtNom = (EditText) findViewById(R.id.EtxtNombre);

        txtNom.setText(VariablesGlobalesAlumnos.Nombre[id]);
        txtApeP.setText(VariablesGlobalesAlumnos.Paterno[id]);
        txtApeM.setText(VariablesGlobalesAlumnos.Materno[id]);
        ano=VariablesGlobalesAlumnos.Ano[id];
        dia=VariablesGlobalesAlumnos.Dia[id];
        mes=VariablesGlobalesAlumnos.Mes[id];
        dateView.setText(dia+"/"+mes+"/"+ano);
        CalcularEdad(ano);
        spinnerCarrera.setSelection(0);
    }
    public void ObtenerDatosEditados(View v){
        String Matri=txtMat.getText().toString();
        String Nom=txtNom.getText().toString();
        String Pat=txtApeP.getText().toString();
        String Mat=txtApeM.getText().toString();
        String Carre=spinnerCarrera.getSelectedItem().toString();
        String ed = String.valueOf(Edad);

        agregaraBasedeDatos(Matri,Nom,Pat,Mat,String.valueOf(dia),String.valueOf(mes),String.valueOf(ano),ed,Carre);

    }
    public void agregaraBasedeDatos (String Matricula, String Nombre, String Paterno, String Materno, String Dia, String Mes, String Ano, String Edad, String Carrera){
        String respuesta = cn.AgregarAlumno("http://tonnyxp20.x10host.com/Escuela/UpdateAlumnos.php", Matricula,Nombre,Paterno, Materno, Dia, Mes, Ano, Edad, Carrera);
        showToast(respuesta);
        ObtenerDatos();
    }

}
