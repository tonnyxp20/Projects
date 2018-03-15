package com.example.lenovow520.basededatos;

/**
 * Created by Isai Lopez on 07/07/2017.
 * @author Isai
 */
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Conexion {
    public String post(String Url, String a){
        try {
            HttpClient cliente = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Url);

            List<NameValuePair> datos = new ArrayList<NameValuePair>();
            datos.add(new BasicNameValuePair("Matricula",a));
            httppost.setEntity(new UrlEncodedFormEntity(datos));
            HttpResponse resp = cliente.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String texto = EntityUtils.toString(ent);
            return texto;
        }catch (Exception e){
            return e.toString();
        }
    }

    public String Agregar(String Url, String Nombre, String Apellido, String Tipo)
    {
        try {
            HttpClient cliente = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Url);

            List<NameValuePair> datos = new ArrayList<NameValuePair>();
            datos.add(new BasicNameValuePair("nombre",Nombre));
            datos.add(new BasicNameValuePair("apellido",Apellido));
            datos.add(new BasicNameValuePair("tipo",Tipo));
            httppost.setEntity(new UrlEncodedFormEntity(datos));
            HttpResponse resp = cliente.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String texto = EntityUtils.toString(ent);
            return texto;
        }catch (Exception e){
            return e.toString();
        }
    }

    public String AgregarAlumno(String Url, String Matricula, String Nombre, String Paterno, String Materno, String Dia, String Mes, String Ano, String Edad, String Carrera)
    {
        try {
            HttpClient cliente = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Url);

            List<NameValuePair> datos = new ArrayList<NameValuePair>();
            datos.add(new BasicNameValuePair("Matricula",Matricula));
            datos.add(new BasicNameValuePair("Nombre",Nombre));
            datos.add(new BasicNameValuePair("Paterno",Paterno));
            datos.add(new BasicNameValuePair("Materno",Materno));
            datos.add(new BasicNameValuePair("Dia",Dia));
            datos.add(new BasicNameValuePair("Mes",Mes));
            datos.add(new BasicNameValuePair("Ano",Ano));
            datos.add(new BasicNameValuePair("Edad",Edad));
            datos.add(new BasicNameValuePair("Carrera",Carrera));
            httppost.setEntity(new UrlEncodedFormEntity(datos));
            HttpResponse resp = cliente.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String texto = EntityUtils.toString(ent);
            return texto;
        }catch (Exception e){
            return e.toString();
        }
    }


}
