package com.example.daniel.sportec.deportes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.baseDatos.BaseDatos;
import com.example.daniel.sportec.baseDatos.SportecApi;
import com.example.daniel.sportec.modelos.Deporte;
import com.example.daniel.sportec.modelos.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DeportesFragment extends Fragment {
    ArrayList<Deporte> lista;
    Gson gson = new Gson();
    BaseDatos db = new BaseDatos();
    SportecApi api;
    User user;
    public static DeportesFragment newInstance() {
        DeportesFragment fragmentoDeportes = new DeportesFragment();
        return fragmentoDeportes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.deportes_agregar_activity, container, false);
        api = new SportecApi(getContext());
        TypeToken<ArrayList<Deporte>> token = new TypeToken<ArrayList<Deporte>>() {
        };
        lista = gson.fromJson(getArguments().getString("sports"), token.getType());
        user = gson.fromJson(getArguments().getString("user"), User.class);
        user.setSports(new ArrayList<String>());

        if (lista.isEmpty()) {
            Toast.makeText(getActivity(), "Deportes vacios",
                    Toast.LENGTH_SHORT).show();
        } else {
            Button boton = (Button) view.findViewById(R.id.deportes_agregar_activity_boton);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    api.updateUserSports(user, getFragmentManager());
                }
            });
            DeportesAdapter adapter = new DeportesAdapter(getActivity(), lista, user);
            GridView list = (GridView) view.findViewById(R.id.deportes_gridview);
            list.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
