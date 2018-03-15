package com.developer.tonny.database.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import com.developer.tonny.database.Conexion;
import com.developer.tonny.database.Encriptado;
import com.developer.tonny.database.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InsertActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private Calendar calendar;
    private Button btnAgregar, btnFecha;
    private int day, month, year, edad;
    private TextView txtEdad;
    private Spinner spinner;
    private String carrera, matricula;
    private EditText edtMatricula, edtNombre, edtApp, edtApm;

    public boolean existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Alta alumno");
        setSpinner();
        setStrictMode();

        edtMatricula = (EditText) findViewById(R.id.idMatricula);
        edtNombre = (EditText) findViewById(R.id.idNombre);
        edtApp = (EditText) findViewById(R.id.idApp);
        edtApm = (EditText) findViewById(R.id.idApm);

        btnAgregar = (Button) findViewById(R.id.idAgregar);
        btnFecha = (Button) findViewById(R.id.idFecha);
        txtEdad = (TextView) findViewById(R.id.idEdad);

        SimpleDateFormat dateFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFecha.format(new Date());
        btnFecha.setText(fecha);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

    public void limpiar() {
        edtMatricula.setText("");
        edtNombre.setText("");
        edtApp.setText("");
        edtApm.setText("");
    }

    public void consultarMatricula(View view) {
        matricula = encode.encriptar(edtMatricula.getText().toString());
        try {
            String respuesta[] = cn.post("http://tonnyxp20.x10host.com/comprobarMatricula.php", matricula).split(",");

            int count = Integer.parseInt(respuesta[0]);

            if (count > 0) {
                existe = true;
            } else {
                existe = false;
            }
            insertar();

        } catch (Exception x) {
            Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertar() {
        matricula = encode.encriptar(edtMatricula.getText().toString());
        String nombre = encode.encriptar(edtNombre.getText().toString());
        String paterno = encode.encriptar(edtApp.getText().toString());
        String materno = encode.encriptar(edtApm.getText().toString());
        String fecha = encode.encriptar(btnFecha.getText().toString());
        String sEdad = encode.encriptar(String.valueOf(edad));

        try {
            if (existe == true) {
                showToast("Ya existe, intente de nuevo.");
                limpiar();
            } else {
                if (matricula.equals("") || nombre.equals("") || paterno.equals("") || materno.equals("")) {
                    showToast("Complete los campos.");
                } else {
                    String respuesta = cn.postEstudiante("http://tonnyxp20.x10host.com/insertarEstudiante.php"
                            , matricula
                            , nombre
                            , paterno
                            , materno
                            , fecha
                            , sEdad
                            , encode.encriptar(carrera));

                    showToast(respuesta);
                    limpiar();
                }
            }
        } catch (Exception x) {
            showToast(x.toString());
        }
    }

    public void setSpinner() {
        spinner = (Spinner) findViewById(R.id.idCarrera);
        List<String> tipos = new ArrayList<String>();
        tipos.add("Sistemas");
        tipos.add("Redes");
        tipos.add("Contabilidad");
        tipos.add("Terapia");
        tipos.add("Mecatronica");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carrera = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
