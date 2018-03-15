package com.developer.tonny.myroutine;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.developer.tonny.myroutine.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeightActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnFecha, btnHora;
    private int dia, mes, ano, hora, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        setTitle("Receta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*btnFecha = (Button) findViewById(R.id.idFecha);
        btnHora = (Button) findViewById(R.id.idHora);
        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);

        SimpleDateFormat dateFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFecha.format(new Date());
        btnFecha.setText(fecha);

        SimpleDateFormat dateHora = new SimpleDateFormat("hh:mm");
        String hora = dateHora.format(new Date());
        btnHora.setText(hora); */
    }

    @Override
    public void onClick(View v) {
        if (v == btnFecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    btnFecha.setText(dayOfMonth + "/" + month + "/" + year );
                }
            } , dia, mes, ano);
            datePickerDialog.show();
        }

        if (v == btnHora) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            min = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog =  new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    btnHora.setText(hourOfDay + ":" + minute);
                }
            } , hora, min, false);
            timePickerDialog.show();
        }
    }
    
    public void guardar(View v) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
