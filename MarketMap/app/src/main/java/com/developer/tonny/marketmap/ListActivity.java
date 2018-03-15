package com.developer.tonny.marketmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ListActivity extends AppCompatActivity {

    private TextView txtNombre, txtLatitud, txtLongitud;
    public CoordinatesActivity menu = new CoordinatesActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setTitle("Lista");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNombre = (TextView) findViewById(R.id.idNombre);
        txtLatitud = (TextView) findViewById(R.id.idLatitud);
        txtLongitud = (TextView) findViewById(R.id.idLongitud);

        String name = "", lat = "", lng = "";
        if (menu.count != 0) {
            for (int i = 0; i < menu.count; i++) {
                name += menu.name[i] + "\n";
                lat += menu.lat[i] + "\n";
                lng += menu.lng[i] + "\n";
            }
            txtNombre.setText(name);
            txtLatitud.setText(lat);
            txtLongitud.setText(lng);
        } else {
            Toast.makeText(getApplicationContext(), "No hay datos existentes", Toast.LENGTH_SHORT).show();
        }
    }

}
