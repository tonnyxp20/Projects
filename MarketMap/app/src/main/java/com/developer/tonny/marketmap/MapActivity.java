package com.developer.tonny.marketmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    SeekBar seekBar;
    Double lat, lng;
    LatLng posicion;
    Marker marcador;
    String name = "";

    public CoordinatesActivity menu = new CoordinatesActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setTitle("Ubicaciones");

        MapFragment fragmento = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragmento.getMapAsync(this);

        setSeekBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        seekBar.setEnabled(false);
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
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        Recibir();
    }

    public void Recibir() {
        if (menu.count != 0) {
            for (int i = 0; i < menu.count; i++) {
                name = menu.name[i];
                lat = Double.parseDouble(menu.lat[i]);
                lng = Double.parseDouble(menu.lng[i]);
                Marcador(lat, lng, name);
            }
            seekBar.setEnabled(true);
        } else {
            Toast.makeText(getApplicationContext(), "No hay datos existentes", Toast.LENGTH_SHORT).show();
            seekBar.setEnabled(false);
        }
    }

    public void Marcador(Double latitud, Double longitud, String nombre) {
        posicion = new LatLng(latitud, longitud);
        marcador = mapa.addMarker(new MarkerOptions().position(posicion).title("Marcador en " + nombre));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(posicion));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_satelite:
                mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;

            case R.id.menu_tereno:
                mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;

            case R.id.menu_hibrido:
                mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
