package com.developer.tonny.design.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.tonny.design.Activities.CitaActivity;
import com.developer.tonny.design.Activities.Medicamento;
import com.developer.tonny.design.R;

public class ThirdFragment extends Fragment {

    private FloatingActionButton fabCreate;

    public ThirdFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        fabCreate = (FloatingActionButton) view.findViewById(R.id.fab_create);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CitaActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

}
