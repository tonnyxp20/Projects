package com.developer.tonny.database;

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
 * Created by Tonny on 7/7/2017.
 */

public class Conexion {

    public String post (String url, String a) {
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);


            List<NameValuePair> listDatos = new ArrayList<NameValuePair>();
            listDatos.add(new BasicNameValuePair("pac", a));


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
