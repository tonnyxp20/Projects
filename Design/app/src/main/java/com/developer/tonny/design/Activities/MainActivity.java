package com.developer.tonny.design.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.developer.tonny.design.FragmentsActivities.ConfiguracionFragment;
import com.developer.tonny.design.FragmentsActivities.CuentaFragment;
import com.developer.tonny.design.FragmentsActivities.EmergenciasFragment;
import com.developer.tonny.design.FragmentsActivities.InicioFragment;
import com.developer.tonny.design.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        setFragmentByDefault();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()){

                    case R.id.menu_inicio:
                        fragment = new InicioFragment();
                        fragmentTransaction = true;
                        break;

                    case R.id.menu_cuenta:
                        fragment = new CuentaFragment();
                        fragmentTransaction = true;
                        break;

                    case R.id.menu_emergencias:
                        fragment = new EmergenciasFragment();
                        fragmentTransaction = true;
                        break;

                    case R.id.menu_configuracion:
                        fragment = new ConfiguracionFragment();
                        fragmentTransaction = true;
                        break;
                }

                if(fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    // Agregamos el toolbar creado
    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Muestra fragmento como main
    private void setFragmentByDefault() {
        changeFragment(new InicioFragment(), navigationView.getMenu().getItem(0));
    }

    // Método para cambiar de fragment
    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
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
            case android.R.id.home:
                // abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
