package com.developer.tonny.mapas;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Button btnMapa;
    Button btnTerreno;
    Button btnHibrido;
    Button btnInterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnMapa = (Button)findViewById(R.id.btnMapa);
        btnTerreno = (Button)findViewById(R.id.btnTerreno);
        btnHibrido = (Button)findViewById(R.id.btnHibrido);
        btnInterior = (Button)findViewById(R.id.btnInterior);

        btnMapa.setOnClickListener(this);
        btnTerreno.setOnClickListener(this);
        btnHibrido.setOnClickListener(this);
        btnInterior.setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMapa:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            break;

            case R.id.btnTerreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            break;

            case R.id.btnHibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            break;

            case R.id.btnInterior:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(-33.86997, 151.2089), 18));
                break;

            default:
                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mexico = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(mexico).title("Marker in México"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.punto) )
                        .anchor(0.0f, 1.0f)
                        .position(latLng));
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "Has pulsado una marca", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
