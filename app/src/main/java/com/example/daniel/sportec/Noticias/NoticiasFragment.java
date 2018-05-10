package com.example.daniel.sportec.Noticias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daniel.sportec.BaseDatos.BaseDatos;
import com.example.daniel.sportec.Objetos.Noticia;
import com.example.daniel.sportec.R;

import java.util.ArrayList;

public class NoticiasFragment extends android.support.v4.app.Fragment  {
    BaseDatos db = new BaseDatos();



    public static NoticiasFragment newInstance() {
        NoticiasFragment fragmentoNoticias = new NoticiasFragment();

        return fragmentoNoticias;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.noticias_feed_layout, container, false);


        ImageView imagen = (ImageView) view.findViewById(R.id.noticias_feed_layout_featured_image_view);
        TextView titulo = (TextView) view.findViewById((R.id.noticias_feed_layout_featured_titulo));

        TextView fecha = (TextView) view.findViewById(R.id.noticias_feed_layout_featured_fecha);

        LinearLayout layoutFeatured = (LinearLayout) view.findViewById(R.id.noticias_feed_layout_featured);
        layoutFeatured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmento;
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmento = new NoticiaDetalleFragment();
                fragmentTransaction.replace(R.id.main_page, fragmento);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });
        ArrayList<Noticia> lista = db.getNoticias("rugby");

        Noticia noticia = lista.get(0);
        noticia.setContenido(getArguments().getString("Contenido"));
        noticia.setFecha(getArguments().getString("Fecha"));
        noticia.setTitulo(getArguments().getString("Titulo"));
        titulo.setText(noticia.getTitulo());;
        fecha.setText(noticia.getFecha());

        db.getNoticias("rugby").remove(0);
        NoticiasAdapter adapter = new NoticiasAdapter(getActivity(), db.getNoticias("rugby"));
        ListView list = (ListView) view.findViewById(R.id.noticias_feed_layout_list_view);
        list.setAdapter(adapter);







        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }





}
