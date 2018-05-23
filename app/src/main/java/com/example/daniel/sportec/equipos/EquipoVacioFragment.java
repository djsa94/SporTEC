package com.example.daniel.sportec.equipos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.modelos.User;

import java.util.ArrayList;

public class EquipoVacioFragment extends Fragment{

    public static EquipoVacioFragment newInstance() {
        EquipoVacioFragment fragmentoEquipos = new EquipoVacioFragment();
        return fragmentoEquipos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.equipo_vacio_fragment, container, false);

        Button botonCrear = (Button) view.findViewById(R.id.equipo_vacio_btn);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
