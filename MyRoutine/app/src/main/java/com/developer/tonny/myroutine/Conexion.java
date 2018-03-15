package com.developer.tonny.myroutine;

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

/**
 * Created by Tonny on 7/25/2017.
 */

public class Conexion {

    public String post (String url, String a) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);


            List<NameValuePair> listDatos = new ArrayList<NameValuePair>();
            listDatos.add(new BasicNameValuePair("dato", a));


            httpPost.setEntity(new UrlEncodedFormEntity(listDatos));
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();

            String text = EntityUtils.toString(entity);
            return text;

        } catch (Exception x) {
            return x.toString();
        }
    }

    public String postUsuario (String url, String u, String p, String t) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);


            List<NameValuePair> listDatos = new ArrayList<NameValuePair>();
            listDatos.add(new BasicNameValuePair("user", u));
            listDatos.add(new BasicNameValuePair("pwd", p));
            listDatos.add(new BasicNameValuePair("type", t));


            httpPost.setEntity(new UrlEncodedFormEntity(listDatos));
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();

            String text = EntityUtils.toString(entity);
            return text;

        } catch (Exception x) {
            return x.toString();
        }
    }

    public String postHistorial (String url, String u, String r, String c, String t, String p) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);


            List<NameValuePair> listDatos = new ArrayList<NameValuePair>();
            listDatos.add(new BasicNameValuePair("usuario", u));
            listDatos.add(new BasicNameValuePair("recorrido", r));
            listDatos.add(new BasicNameValuePair("calorias", c));
            listDatos.add(new BasicNameValuePair("tiempo", t));
            listDatos.add(new BasicNameValuePair("pasos", p));


            httpPost.setEntity(new UrlEncodedFormEntity(listDatos));
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();

            String text = EntityUtils.toString(entity);
            return text;

        } catch (Exception x) {
            return x.toString();
        }
    }

}
