package com.developer.tonny.mapa;

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
    public LatLng pos;

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
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, progress));
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

        pos = new LatLng(39.8672, -105.0041);
        mapa.addMarker(new MarkerOptions().position(pos).title("Marcador en Denver"));
        LatLng cdj = new LatLng(19.0779, -103.8846);
        mapa.addMarker(new MarkerOptions().position(cdj).title("Marcador en Cd. Juárez"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(pos));

        // mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));

        /*mapa.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mapa.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker) )
                        .anchor(0.0f, 1.0f)
                        .position(latLng));
            }
        });*/
    }

}
