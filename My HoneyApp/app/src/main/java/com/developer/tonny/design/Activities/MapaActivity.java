package com.developer.tonny.design.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.tonny.design.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private Double lat, lng;
    private LatLng position;
    private Marker marker;
    private SeekBar seekBar;
    private List<Address> addresses;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        setSeekBar();

        MapFragment fragmento = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragmento.getMapAsync(this);
    }

    protected void setSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, progress));
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
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

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

    public void Marcador(final Double latitud, final Double longitud) {
        position = new LatLng(latitud, longitud);

        if (marker != null) {
            marker.remove();
        }

        // Se mueve el marcador
        marker = gMap.addMarker(new MarkerOptions()
                .position(position)
                .title("Yo estoy aquí")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                try {
                    addresses = geocoder.getFromLocation(latitud, longitud, 1);
                } catch (IOException e){
                    e.printStackTrace();
                }

                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();

                Toast.makeText(getApplicationContext(), "Dirección: " + address + "\n" +
                                "Ciudad: " + city + "\n" +
                                "Estado: " + state + "\n" +
                                "País: " + country + "\n" +
                                "Código Postal: " + postalCode
                        , Toast.LENGTH_LONG).show();

                return false;
            }
        });

        gMap.moveCamera(CameraUpdateFactory.newLatLng(position));

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
