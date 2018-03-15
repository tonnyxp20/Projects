package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovow520.basededatos.Valores.VariablesGlobalesUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtNom, txtApe;
    String Tip;
    public Conexion cn = new Conexion();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void agregaraBasedeDatos (String nombre, String apellido, String edad){
        String respuesta = cn.Agregar("http://atime.x10host.com/consulta.php", nombre,apellido,edad);
        showToast(respuesta);
        ObtenerDatos();
    }

    public void obtener (View v){
        RadioButton radiobu = (RadioButton) findViewById(R.id.rdbA) ;
        if(radiobu.isChecked()){
           Tip="admin";
        }
        else{
            Tip="General";
        }
        txtNom = (EditText) findViewById(R.id.textViewNombre);
        txtApe = (EditText) findViewById(R.id.textViewApellidos);
        String Nom = txtNom.getText().toString();
        String Ape = txtApe.getText().toString();
        agregaraBasedeDatos(Nom,Ape,Tip);
        txtNom.setText("");
        txtApe.setText("");
    }
    public void ObtenerDatos(){
        String respuesta = cn.post("http://tonnyxp20.x10host.com/Escuela/consulta.php", "");
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
    }
    public void showToast(String msg){
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }
}
