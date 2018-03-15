package com.developer.tonny.myroutine;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class DefaultActivity extends AppCompatActivity {

    private Button btnSteps;
    private TextView txtPasos,txtDistancia,txtCalorias;

    boolean start = false;
    Location locationStart, locationOnMoving;
    int totalSteps = 0;
    double lastDistanceM = -1;
    double distanceRecorred = 0;
    double tempDistanceM = 0;
    int calorias = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        setTitle("Invitado");
        setSteps();

    }

    private void setSteps() {
        btnSteps = (Button) findViewById(R.id.idRound);
        txtCalorias = (TextView)findViewById(R.id.idCalorias);
        txtDistancia = (TextView) findViewById(R.id.idDistancia);
        txtPasos = (TextView) findViewById(R.id.idPasos);
        btnSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!start)
                {
                    start=true;
                    Ubicar();
                } else {
                    stopStepCounter();
                }
            }
        });
    }

    private void Ubicar()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(), "Habilita el permiso desde ajustes", Toast.LENGTH_LONG).show();
            return;
        }
        LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);


        btnSteps.setText("Iniciando...");
        txtDistancia.setText("Recorrido: \n0");
        txtCalorias.setText("Calorias: \n0");
        txtPasos.setText("Pasos: \n0");
        txtPasos.setVisibility(View.VISIBLE);
        txtDistancia.setVisibility(View.VISIBLE);
        txtCalorias.setVisibility(View.VISIBLE);
    }

    public void distanceTraveled()
    {

        double distanceM = locationStart.distanceTo(locationOnMoving);
        if(distanceM >= 0.72 && distanceM <= 4 && lastDistanceM != distanceM)
        {
            if(lastDistanceM != -1) {
                totalSteps += 1;
            }
            lastDistanceM = distanceM;
            locationStart = locationOnMoving;
            distanceRecorred += 0.72;
        }

        btnSteps.setText("Detener");
        txtPasos.setText("Pasos: \n" + totalSteps);
        txtDistancia.setText("Recorrido: \n" + (int)distanceRecorred + " m");
        calorias = (int)(distanceRecorred * 0.2388);
        txtCalorias.setText("Calorias: \n" + calorias);
    }

    public void stopStepCounter()
    {
        btnSteps.setText("Iniciar");
        start = false;
        totalSteps = 0;
        lastDistanceM = -1;
        tempDistanceM = 0;
        distanceRecorred = 0;
        locationOnMoving = null;
        locationStart = null;
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location)
        {
            if(start)
            {
                if (locationStart == null) {
                    locationStart = location;
                }
                locationOnMoving = location;
                distanceTraveled();
            }
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
        @Override
        public void onProviderEnabled(String provider)
        {
        }
        @Override
        public void onProviderDisabled(String provider)
        {
        }
    };

}
