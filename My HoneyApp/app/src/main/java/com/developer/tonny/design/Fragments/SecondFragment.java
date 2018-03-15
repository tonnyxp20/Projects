package com.developer.tonny.design.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.developer.tonny.design.Activities.Medicamento;
import com.developer.tonny.design.R;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private ListView listView;
    private FloatingActionButton fabAdd;

    public SecondFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        // Lógica para capturar los elementos de la UI

        listView = (ListView) view.findViewById(R.id.idListView);

        fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Medicamento.class);
                startActivity(i);
            }
        });

        recibirDatos();

        return view;
    }

    protected void recibirDatos() {
        Medicamento med = new Medicamento();
        String[] unidad = new String[med.count];

        if (med.count != 0) {
            for (int i = 0; i < med.count; i++) {
                unidad[i] = med.unidad[i];
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, unidad);
        listView.setAdapter(adapter);
    }
}
