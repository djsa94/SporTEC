package com.example.daniel.sportec.BaseDatos;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.daniel.sportec.Noticias.NoticiasFragment;
import com.example.daniel.sportec.Objetos.Noticia;
import com.example.daniel.sportec.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    private DatabaseReference mDatabase;

    public BaseDatos() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void getNoticias(String deporte, FragmentManager fragmentManager){

        final ArrayList<Noticia> noticias = new ArrayList<Noticia>();

        final FragmentManager fm = fragmentManager;
        mDatabase.child("Deportes").child(deporte).child("Noticias").addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Gson gson = new Gson();
                for (DataSnapshot noticiaSnapshot: snapshot.getChildren()) {

                Noticia noticia = noticiaSnapshot.getValue(Noticia.class);
                noticias.add(noticia);
                    Log.e("Post", noticia.getTitulo());
                    Log.e("Post", noticia.getContenido());
                    Log.e("Post", noticia.getFecha());
                }

                Bundle bundle = new Bundle();
                bundle.putString("Noticias", gson.toJson(noticias));
                Fragment fragmentoNuevo = new NoticiasFragment();
                fragmentoNuevo.setArguments(bundle);
                fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();

            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //Log.e("The read failed: " ,firebaseError.getMessage());
            }



        });

    }
}
