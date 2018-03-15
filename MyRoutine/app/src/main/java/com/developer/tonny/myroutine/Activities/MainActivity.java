package com.developer.tonny.myroutine.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.myroutine.Adapters.Encriptado;
import com.developer.tonny.myroutine.Conexion;
import com.developer.tonny.myroutine.R;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private Button btnSteps;
    private TextView txtPasos,txtDistancia,txtCalorias;
    private FabSpeedDial fabSpeedDial;

    boolean start = false;
    Location locationStart, locationOnMoving;
    int totalSteps = 0;
    double lastDistanceM = -1;
    double distanceRecorred = 0;
    double tempDistanceM = 0;
    int calorias = 0;

    Chronometer crono;

    public String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("My Routine");
        setStrictMode();
        setSteps();
        setFapSpeedDial();

        crono = (Chronometer) findViewById(R.id.chronometer);
    }

    public void insertar() {
        nombre = getIntent().getStringExtra("name");
        int recorrido = (int)distanceRecorred;
        try {
            String x = cn.postHistorial("http://tonnyxp20.x10host.com/Podometro/insertarHistorial.php"
                    , encode.shaEncryption(nombre)
                    , encode.encriptar(String.valueOf(recorrido))
                    , encode.encriptar(String.valueOf(calorias))
                    , encode.encriptar(crono.getText().toString())
                    , encode.encriptar(String.valueOf(totalSteps)));

            showToast(x);

        } catch (Exception x) {
            showToast(x.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idAjustes:
                nombre = getIntent().getStringExtra("name");
                Intent settings = new Intent(this, SettingsActivity.class);
                settings.putExtra("name", nombre);
                startActivity(settings);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setFapSpeedDial() {
        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true; // false: para mostrar el menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.action_record:
                        nombre = getIntent().getStringExtra("name");
                        Intent i = new Intent(getApplicationContext(), RecordActivity.class);
                        i.putExtra("name", nombre);
                        startActivity(i);
                        return true;

                    case R.id.action_dieta:
                        Intent dieta = new Intent(getApplicationContext(), DietActivity.class);
                        startActivity(dieta);
                        return true;

                    case R.id.action_fitness:
                        Intent fitness = new Intent(getApplicationContext(), FitnessActivity.class);
                        startActivity(fitness);
                        return true;
                }
                return false;
            }

            @Override
            public void onMenuClosed() {

            }
        });
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

        crono.setBase(SystemClock.elapsedRealtime());

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
        crono.start();

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
        insertar();

        btnSteps.setText("Iniciar");
        start = false;
        totalSteps = 0;
        lastDistanceM = -1;
        tempDistanceM = 0;
        distanceRecorred = 0;
        locationOnMoving = null;
        locationStart = null;

        crono.stop();
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

