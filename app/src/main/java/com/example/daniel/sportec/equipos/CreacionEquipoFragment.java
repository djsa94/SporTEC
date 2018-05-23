package com.example.daniel.sportec.equipos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniel.sportec.R;

public class CreacionEquipoFragment extends Fragment {
    public static CreacionEquipoFragment newInstance() {
        CreacionEquipoFragment fragmentoEquipos = new CreacionEquipoFragment();
        return fragmentoEquipos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.equipo_crear_fragment, container, false);

        EditText nombreEquipo = (EditText) view.findViewById(R.id.equipo_crear_entrada_nombre);
        EditText descripcionEquipo = (EditText) view.findViewById(R.id.equipo_crear_entrada_descripcion);

        Button botonAgregarFoto = (Button) view.findViewById(R.id.equipo_crear_boton_foto);
        Button botonCrear = (Button) view.findViewById(R.id.equipo_crear_boton_crear);

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botonAgregarFoto.setOnClickListener(new View.OnClickListener() {
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
