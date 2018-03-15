package com.developer.tonny.mapa;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

/*
Coordenadas GPS en Denver
Latitud : 39.8672
Longitud : -105.0041
*/

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private SeekBar seekBar;
    private Double lat, lng;
    private LatLng posicion;
    private Marker marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment fragmento = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragmento.getMapAsync(this);

        setSeekBar();


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

    @Override
    public void onMapReady(final GoogleMap map) {
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

        //marcador = mapa.addMarker(new MarkerOptions().position(posicion).title("Usted esta aquí"));
        // mapa.moveCamera(CameraUpdateFactory.newLatLng(posicion));

        // Se mueve el marcador
        marcador = mapa.addMarker(new MarkerOptions()
                .position(posicion)
                .draggable(true));

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
