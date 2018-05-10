package com.example.daniel.sportec.Noticias;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.sportec.Objetos.Noticia;
import com.example.daniel.sportec.R;
import com.google.gson.Gson;

public class NoticiaDetalleFragment extends Fragment {


    public static NoticiaDetalleFragment newInstance() {
        NoticiaDetalleFragment fragmentoNoticia = new NoticiaDetalleFragment();

        return fragmentoNoticia;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.noticias_detalle_layout, container, false);
        ImageView imagen = (ImageView) view.findViewById(R.id.noticias_detalle_imagen);
        TextView titulo = (TextView) view.findViewById((R.id.noticias_detalle_titulo));
        TextView contenido = (TextView) view.findViewById(R.id.noticias_detalle_contenido);
        TextView fecha = (TextView) view.findViewById(R.id.noticias_detalle_fecha);

        Gson gson = new Gson();

        Noticia noticia = gson.fromJson(getArguments().getString("noticia"), Noticia.class);
        Log.e("ANTES ERROR", getArguments().getString("noticia"));
        titulo.setText(noticia.getTitulo());
        contenido.setText(noticia.getContenido());
        fecha.setText(noticia.getFecha());




        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


}
