package com.developer.tonny.database;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    public Conexion cn = new Conexion();
    private Calendar calendar;
    private Spinner spinnerMatricula, spinnerCarrera;
    private EditText edtNombre, edtPaterno, edtMaterno;
    private Button btnGuardar, btnFecha;
    private TextView txtEdad;
    private int day, month, year, edad;

    List<String> lista = new ArrayList<String>();
    public String matricula, carrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Modificar estudiante");
        setStrictMode();
        setSpinnerCarrera();
        consultaMatricula();
        setSpinnerMatricula();

        edtNombre = (EditText) findViewById(R.id.editTextNombre);
        edtPaterno = (EditText) findViewById(R.id.editTextPaterno);
        edtMaterno = (EditText) findViewById(R.id.editTextMaterno);

        btnGuardar = (Button) findViewById(R.id.buttonGuardar);
        btnGuardar.setEnabled(false);

        btnFecha = (Button) findViewById(R.id.buttonFecha);
        txtEdad = (TextView) findViewById(R.id.textViewEdad);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public void limpiar() {
        edtNombre.setText("");
        edtPaterno.setText("");
        edtMaterno.setText("");
        txtEdad.setText("");
    }

    public void actualizar(View view) {
        String nombre = edtNombre.getText().toString();
        String paterno = edtPaterno.getText().toString();
        String materno = edtMaterno.getText().toString();
        String fecha = btnFecha.getText().toString();
        String sEdad = txtEdad.getText().toString();

        try {

            if (nombre.equals("") || paterno.equals("") || materno.equals("")) {
                showToast("Complete los campos.");
            } else {
                String respuesta = cn.postEstudiante("http://tonnyxp20.x10host.com/actualizarEstudiante.php"
                        , matricula
                        , nombre
                        , paterno
                        , materno
                        , fecha
                        , sEdad
                        , carrera);

                showToast(respuesta);
                limpiar();
                btnGuardar.setEnabled(false);
            }

        } catch (Exception x) {
            showToast(x.toString());
        }
    }

    public void setSpinText(Spinner spinner, String text) {
        for(int i = 0; i < spinner.getAdapter().getCount(); i++)
        {
            if(spinner.getAdapter().getItem(i).toString().contains(text))
            {
                spinner.setSelection(i);
            }
        }

    }

    public void buscar(View view) {
        btnGuardar.setEnabled(true);
        try {

            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarEstudiante.php", matricula).split(",");

            edtNombre.setText(respuesta[0]);
            edtPaterno.setText(respuesta[1]);
            edtMaterno.setText(respuesta[2]);

            String fecha[] = respuesta[3].split("/");
            showDate(Integer.parseInt(fecha[2])
                    , Integer.parseInt(fecha[1])
                    , Integer.parseInt(fecha[0]));

            txtEdad.setText(respuesta[4] + " años");
            setSpinText(spinnerCarrera, respuesta[5]);

        } catch (Exception x) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        // Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        btnFecha.setText(new StringBuilder()
                .append(day).append("/")
                .append(month).append("/")
                .append(year));

        edad = 2017 - year;
        txtEdad.setText(edad + " años");
    }

    public void consultaMatricula() {
        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/consultarMatricula.php", "").split(",");

            int num = Integer.parseInt(respuesta[0]);

            lista.clear();
            if(num > 0) {
                for (int i = 0; i < num; i++) {
                    lista.add(respuesta[i+1]);
                }
            } else {
                showToast("No hay datos.");
            }

        } catch (Exception e) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setSpinnerMatricula() {
        spinnerMatricula = (Spinner) findViewById(R.id.spinnerMatricula);

        consultaMatricula();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatricula.setAdapter(adapter);

        spinnerMatricula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matricula = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setSpinnerCarrera() {
        spinnerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);
        List<String> tipos = new ArrayList<String>();
        tipos.add("Sistemas");
        tipos.add("Redes");
        tipos.add("Contabilidad");
        tipos.add("Terapia");
        tipos.add("Mecatronica");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapter);

        spinnerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carrera = parent.getItemAtPosition(position).toString();
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
