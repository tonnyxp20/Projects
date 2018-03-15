package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovow520.basededatos.ListView.MyAdapter;
import com.example.lenovow520.basededatos.Valores.VariablesGlobalesUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Ver extends AppCompatActivity {
    // private ListView Registro;
    // private List<String> names;
    //private List<String> respuesta;
    public Conexion cn = new Conexion();

    private TextView txtLista;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle("Registro");
        ObtenerDatos();

    }

    public void back(View v) {
        Intent i = new Intent(getApplicationContext(), Menu.class);
        startActivity(i);
    }

    public void CrearListView() {
        // Registro = (ListView) findViewById(R.id.listRegistro);
        txtLista = (TextView) findViewById(R.id.idLista);
        // names = new ArrayList<String>();
        // respuesta = new ArrayList<String>();
        String respuesta = "";
        if (VariablesGlobalesUser.contador != 0) {
            for (int i = 0; i <= VariablesGlobalesUser.contador; i++) {
                // names.add(VariablesGlobalesUser.Usuario[i]);
                respuesta += "Id: " + VariablesGlobalesUser.ID[i] + "\nUsuario: " + VariablesGlobalesUser.Usuario[i] + "\nContraseña: " + VariablesGlobalesUser.Password[i] + "\nTipo: " + VariablesGlobalesUser.Tipo[i] + "\n";
            }
        } else {
            showToast("No hay datos guardados");
        }

        /* Registro.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), position+1+" "+Menu.nombre[position]+" Latitud: "+Menu.Latitude[position]+" Longitud: "+Menu.Longitude[position], Toast.LENGTH_SHORT).show();

            }
        });*/

        // MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names, respuesta);
        // Registro.setAdapter(myAdapter);
        txtLista.setText(respuesta);

    }
    public void ObtenerDatos(){
        String respuesta = cn.post("http://tonnyxp20.x10host.com/Escuela/conlsut.php", "");
        try{
            JSONArray jsonarray = new JSONArray(respuesta);
            for(int i=0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                VariablesGlobalesUser.ID[i] = jsonobject.getInt("ID");
                VariablesGlobalesUser.Usuario[i] = jsonobject.getString("Nombre");
                VariablesGlobalesUser.Password[i]= jsonobject.getString("Pass");
                VariablesGlobalesUser.Tipo[i]= jsonobject.getString("Tipo");
                VariablesGlobalesUser.contador=i;
            }
        }catch (JSONException e){
            showToast(e.toString());
        }
        CrearListView();
    }


    public void showToast(String msg){
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

}
