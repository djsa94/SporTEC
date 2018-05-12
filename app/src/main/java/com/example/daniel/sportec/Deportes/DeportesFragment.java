package com.example.daniel.sportec.Deportes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.sportec.BaseDatos.BaseDatos;
import com.example.daniel.sportec.Noticias.NoticiaDetalleFragment;
import com.example.daniel.sportec.Noticias.NoticiasAdapter;
import com.example.daniel.sportec.Noticias.NoticiasFragment;
import com.example.daniel.sportec.Objetos.Deporte;
import com.example.daniel.sportec.Objetos.Noticia;
import com.example.daniel.sportec.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DeportesFragment extends Fragment {
    ArrayList<Deporte> lista;
    Gson gson = new Gson();
    BaseDatos db = new BaseDatos();

    public static DeportesFragment newInstance() {
        DeportesFragment fragmentoDeportes = new DeportesFragment();


        return fragmentoDeportes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.deportes_agregar_activity, container, false);

        TypeToken<ArrayList<Deporte>> token = new TypeToken<ArrayList<Deporte>>() {};
        lista = gson.fromJson(getArguments().getString("deportes"), token.getType());


        if(lista.isEmpty()){
            Toast.makeText(getActivity(), "Deportes vacios",
                    Toast.LENGTH_SHORT).show();
        }else {
            Button boton = (Button) view.findViewById(R.id.deportes_agregar_activity_boton);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.ingresarPreferencias();
                    db.getPreferencias(getFragmentManager(), FirebaseAuth.getInstance().getCurrentUser());
                }
            });





            DeportesAdapter adapter = new DeportesAdapter(getActivity(), lista,db);
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
