package com.developer.tonny.locationgps.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.tonny.locationgps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.cont;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.count;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.latitud;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.longitud;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.names;

public class GpsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private Double lat, lng;
    private LatLng posicion;
    private Marker marcador;
    private Spinner spinner;
    private Button btnVer;
    private SeekBar seekBar;

    private boolean ver = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        setTitle("GPS");
        setSeekBar();
        // setSpinner();
        setBtnVer();

        MapFragment fragmento = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragmento.getMapAsync(this);

    }

    protected void setSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setBtnVer() {
        btnVer = (Button) findViewById(R.id.idVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ver == true){
                    mostrarUbicaciones();
                    ver = false;
                } else {
                    removerMarcadores();
                }
            }
        });
    }

    public void removerMarcadores() {
        if (ver == false){
            mapa.clear();
            ver = true;
        }
    }

    public void mostrarUbicaciones() {
        if (cont != 0){
            for (int i = 0; i < cont; i++){
                Double latDouble = Double.parseDouble(latitud[i]);
                Double lngDouble = Double.parseDouble(longitud[i]);
                marcadoresUbicacion(latDouble, lngDouble);
            }
        } else {
            Toast.makeText(getApplicationContext(), "No hay ubicaciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void marcadoresUbicacion(Double lati, Double longi) {
        LatLng pos = new LatLng(lati, longi);
        marcador = mapa.addMarker(new MarkerOptions()
                .position(pos)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
    }

    public void setSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);

        String[] users = new String[count];
        if (count != 0) {
            for (int i = 0; i < count; i++){
                    users[i] = names[i];
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapa = map;

        Ubicar();
    }

    private void Ubicar() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Habilita el permiso desde ajustes", Toast.LENGTH_LONG).show();
            return;
        }

        LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // Actualización del mapa
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        actualizarMapa(loc);
    }

    public void actualizarMapa(Location loc) {
        if (loc != null) {
            lat = loc.getLatitude();
            lng = loc.getLongitude();
            Marcador(lat, lng);
        }
    }

    public void Marcador(Double latitud, Double longitud) {
        posicion = new LatLng(latitud, longitud);

        if (marcador != null) {
            marcador.remove();
        }

        // Se mueve el marcador
        marcador = mapa.addMarker(new MarkerOptions()
                .position(posicion)
                .title("Yo estoy aquí")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mapa.moveCamera(CameraUpdateFactory.newLatLng(posicion));

    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location loc) {
            actualizarMapa(loc);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
