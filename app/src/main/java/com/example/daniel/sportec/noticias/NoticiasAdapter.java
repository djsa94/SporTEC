package com.example.daniel.sportec.noticias;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.sportec.baseDatos.BaseDatos;
import com.example.daniel.sportec.modelos.Noticia;
import com.example.daniel.sportec.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class NoticiasAdapter extends ArrayAdapter<Noticia>{

    Context contexto;
    Gson gson = new Gson();

    public NoticiasAdapter(Context context, ArrayList<Noticia> noticias) {
        super(context, 0, noticias);
        this.contexto = context;
        Log.e("Probando lista", String.valueOf(noticias.size()));
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.noticias_feed_regular_layout, parent, false);
        }

        final Noticia noticia = getItem(position);
        Log.e("Probando single", String.valueOf(position));
        String titulo = noticia.getTitulo();
        TextView textView = (TextView) convertView.findViewById(R.id.noticias_feed_layout_regular_titulo);
        textView.setText(titulo.subSequence(0,titulo.length()));
        TextView textViewfecha = (TextView) convertView.findViewById(R.id.noticias_feed_layout_regular_fecha);
        //textViewfecha.setText(noticia.getFecha().subSequence(0,noticia.getFecha().length()));
        ImageView imagen = (ImageView) convertView.findViewById(R.id.noticias_feed_layout_regular_image_view);
        BaseDatos db = new BaseDatos();
//        db.getImagen(noticia.getImagen(), imagen);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmento;
                FragmentManager fragmentManager = ((AppCompatActivity)contexto).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmento = new NoticiaDetalleFragment();

                Bundle arguments = new Bundle();
                arguments.putString( "noticia" , gson.toJson(noticia));
                fragmento.setArguments(arguments);

                fragmentTransaction.replace(R.id.main_page, fragmento);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });




        return convertView;
    }
}
