package com.developer.tonny.locationgps.Activities;

import android.content.Intent;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SeekBar;
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

import java.text.DecimalFormat;

import io.github.yavski.fabspeeddial.FabSpeedDial;

import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.cont;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.latitud;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.longitud;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.nombre;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.ubicacion;
import static com.developer.tonny.locationgps.GlobalVariables.ArrayGlobal.user;


public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private SeekBar seekBar;
    private LatLng pos, position;
    public static Double lat, lng;

    private FabSpeedDial fabSpeedDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        setTitle("Ubicación");
        setSeekBar();

        MapFragment fragmento = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragmento.getMapAsync(this);

        setFapSpeedDial();
        seekBar.setEnabled(false);
    }

    public void setSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(position, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                // eventos
                switch (menuItem.getItemId()) {

                    case R.id.action_save:
                        guardarUbicaciones();
                        return true;

                    case R.id.action_list:
                        listActivity();
                        return true;
                }

                return false;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

    public void listActivity() {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(i);
    }

    public void guardarUbicaciones() {

        try {
            nombre[cont] = user;
            DecimalFormat df = new DecimalFormat("#.######");
            String latString = df.format(lat);
            String lngString = df.format(lng);

            ubicacion[cont] = latString + ", " + lngString;

            Toast.makeText(getApplicationContext(), "Se ha guardado la ubicación", Toast.LENGTH_SHORT).show();

            latitud[cont] = lat.toString();
            longitud[cont] = lng.toString();

            cont++;
        } catch (Exception e){

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        pos = new LatLng(39.8672, -105.0041);
        mapa.addMarker(new MarkerOptions()
                .position(pos)
                .title("Marcador de " + user)
                .draggable(true)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mapa.moveCamera(CameraUpdateFactory.newLatLng(pos));

        mapa.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                // Cuando se arrastra un marcador inicialmente
                seekBar.setEnabled(true);
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                // Cuando se arrastra el marcador en forma constante
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                // Al final de la operación de arrastre
                position = marker.getPosition();
                lat = position.latitude;
                lng = position.longitude;
            }
        });

    }



}
