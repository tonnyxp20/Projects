package com.developer.tonny.design.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.developer.tonny.design.Adapters.PagerAdapter;
import com.developer.tonny.design.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My HoneyApp");
        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);
    }

    // Agregamos el toolbar creado
    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    // Añádimos los tabs
    private void setTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Registro"));
        tabLayout.addTab(tabLayout.newTab().setText("Medicamentos"));
        tabLayout.addTab(tabLayout.newTab().setText("Citas"));
    }

    // El adaptador nos permite acceder a la clase y adjuntamos el adaptador
    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    // Cuando haya una accion sobre el tab
    private void setListenerTabLayout(final ViewPager viewPager) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            // Muestra el tab seleccionado
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            // Se mueve de un tab a otro
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            // Ha sido reelegido el mismo tab
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // Menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.menu_mapa:
                Intent mapa = new Intent(getApplicationContext(), MapaActivity.class);
                startActivity(mapa);
                return true;

            case R.id.menu_cuenta:
                Intent cuenta = new Intent(getApplicationContext(), CuentaActivity.class);
                startActivity(cuenta);
                return true;

            case R.id.menu_emergencias:
                Intent emergencia = new Intent(getApplicationContext(), Emergencias.class);
                startActivity(emergencia);
                return true;

            case R.id.menu_ajustes:
                Intent ajustes = new Intent(getApplicationContext(), Ajustes.class);
                startActivity(ajustes);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
