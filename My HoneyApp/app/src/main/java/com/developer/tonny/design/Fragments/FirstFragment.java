package com.developer.tonny.design.Fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.tonny.design.Activities.Emergencias;
import com.developer.tonny.design.R;

public class FirstFragment extends Fragment {

    private FloatingActionButton fabPhone;
    public String strtext;

    public FirstFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        setFabPhone(view);
        return view;
    }

    private void setFabPhone(View view) {
        fabPhone = (FloatingActionButton)view.findViewById(R.id.fab_phone);
        fabPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), "Mantenga pulsado el botón para llamar", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        fabPhone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Emergencias eme = new Emergencias();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel: " + eme.contacto));

                if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Por favor concede el permiso para llamar", Toast.LENGTH_SHORT).show();
                    requestPermission();
                } else {
                    startActivity(i);
                }
                return false;
            }
        });
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
    }

}
