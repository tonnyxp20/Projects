package com.example.lenovow520.basededatos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovow520.basededatos.Valores.VariablesGlobalesUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    String Usuario = "";
    String Password = "";
    boolean us = false;
    public static int id;
    public Conexion cn = new Conexion();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ObtenerDatos();
        //Boton de Iniciar
        Button Tab = (Button) findViewById(R.id.btnSplash);
        Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
        //Boton de registrar
        Button Reg;
        Reg = (Button) findViewById(R.id.btnRegis);

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent v2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(v2);
            }
        });
    }
    //Buscar el usuario
    public void buscar()
    {
        EditText ObUser = (EditText) findViewById(R.id.txtUser);
        EditText ObPass = (EditText) findViewById(R.id.txtPass);
        String User = ObUser.getText().toString();
        String Pass = ObPass.getText().toString();
        for(int i = 0; VariablesGlobalesUser.contador>=i; i++)
        {
            if (User.equals(VariablesGlobalesUser.Usuario[i])){
                id = i;
                us = true;
                Usuario=User;
                Password=Pass;
                verificar();
            }

        }
        if (us==false){
            showToast("Usuario Incorrecto");
        }
        ObUser.setText("");
        ObPass.setText("");

    }

    //Si encuentra el usuario verifica el password
    public void verificar() {

        if (Password.equals(VariablesGlobalesUser.Password[id])) {
            showToast("Bienvenido " + Usuario);
            abrir();
        } else {
            showToast("Contraseña Incorrecto");
        }
    }

    //abre el el otro activity dependiendo del tipo de usuario que es
    public void abrir() {
        if(VariablesGlobalesUser.Tipo[id].equals("admin")){
            showToast("Usuario tipo A");
            Intent menuIntent = new Intent().setClass(getApplicationContext(), Menu.class);
            startActivity(menuIntent);
            finish();
        }
        else{
            showToast("Usuario tipo G");
            Intent menuIntent = new Intent().setClass(getApplicationContext(), ReportesAlumnos.class);
            startActivity(menuIntent);
            finish();
        }

    }
    //Obtiene los datos de la base de datos
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
    }
    public void showToast(String msg){
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }
}
