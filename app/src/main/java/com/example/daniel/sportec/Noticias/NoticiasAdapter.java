package com.example.daniel.sportec.Noticias;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.daniel.sportec.Objetos.Noticia;
import com.example.daniel.sportec.R;

import java.util.ArrayList;

public class NoticiasAdapter extends ArrayAdapter<Noticia>{

    Context contexto;
    LayoutInflater mInflater;

    public NoticiasAdapter(Context context, ArrayList<Noticia> noticias) {
        super(context, 0, noticias);
        this.contexto = context;
        mInflater = LayoutInflater.from(contexto);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.noticias_feed_regular_layout, parent, false);
        }

        final Noticia noticia = getItem(position);

        String titulo = noticia.getTitulo();
        TextView textView = (TextView) convertView.findViewById(R.id.noticias_feed_layout_regular_titulo);
        textView.setText(titulo.subSequence(0,titulo.length()));



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmento;
                FragmentManager fragmentManager = ((AppCompatActivity)contexto).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmento = new NoticiaDetalleFragment();

                Bundle arguments = new Bundle();
                arguments.putString( "Titulo" , noticia.getTitulo());
                arguments.putString("Contenido", noticia.getContenido());
                arguments.putString("Fecha", noticia.getFecha());
                fragmento.setArguments(arguments);

                fragmentTransaction.replace(R.id.main_page, fragmento);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });



        textView.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View convertView) {
//                Fragment fragmento;
//                FragmentManager fragmentManager = ((AppCompatActivity)contexto).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                fragmento = new administracion_editar_voluntario_Fragment();
//
//                Bundle arguments = new Bundle();
//                fragmento.setArguments(arguments);
//
//
//                fragmentTransaction.replace(R.id.main_page, fragmento);
//                fragmentTransaction.addToBackStack(null);
//
//                fragmentTransaction.commit();

            }
        });

        return convertView;
    }
}
