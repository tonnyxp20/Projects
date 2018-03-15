package com.example.lenovow520.basededatos.ListView;

/**
 * Created by Lenovo W520 on 15/06/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovow520.basededatos.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;
    private List<String> respuesta;

    public MyAdapter(Context context, int layout, List<String> names, List<String> respuesta)
    {
        this.context = context;
        this.layout = layout;
        this.names = names;
        this.respuesta = respuesta;
    }



    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //copiamos la vista
        View v = convertView;
        //Rellanamos la lista
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v=layoutInflater.inflate(R.layout.list_item, null);
        //nos traemos el valor actual
        String currentName = names.get(position);
        String currentName2= respuesta.get(position);
        //currentName = (String)getItem(position);
        //referenciamos el elemento y lo rellenamos
        TextView textView = (TextView)v.findViewById(R.id.txtPregunta);
        TextView textView2 = (TextView)v.findViewById(R.id.txtRespuesta);
        textView.setText(currentName);
        textView2.setText(currentName2);
        //devolvemos rellenada y modificada los datos
        return v;
    }
}