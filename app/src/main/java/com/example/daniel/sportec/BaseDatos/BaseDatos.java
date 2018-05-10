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
import com.google.firebase.auth.FirebaseUser;
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
    public void getNoticias(FragmentManager fragmentManager, ArrayList<String> deportesIn, ArrayList<Noticia> noticiasIn){
        final ArrayList<String> deportes = deportesIn;
        final ArrayList<Noticia> noticias = noticiasIn;

        final FragmentManager fm = fragmentManager;

        if(deportes.isEmpty()){
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("Noticias", gson.toJson(noticias));
            Fragment fragmentoNuevo = new NoticiasFragment();
            fragmentoNuevo.setArguments(bundle);
            fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();
        }else{
            mDatabase.child("Deportes").child(deportes.get(0)).child("Noticias").addValueEventListener(new ValueEventListener() {



                @Override
                public void onDataChange(DataSnapshot snapshot) {





                    for (DataSnapshot noticiaSnapshot: snapshot.getChildren()) {

                        Noticia noticia = noticiaSnapshot.getValue(Noticia.class);
                        noticias.add(noticia);
                        Log.e("Post", noticia.getTitulo());
                        Log.e("Post", noticia.getContenido());
                        Log.e("Post", noticia.getFecha());
                    }
                    deportes.remove(0);
                    getNoticias(fm, deportes, noticias);



                }
                @Override
                public void onCancelled(DatabaseError firebaseError) {
                    //Log.e("The read failed: " ,firebaseError.getMessage());
                }



            });
        }


    }


    public void getPreferencias(final FragmentManager fragmentManager, FirebaseUser user ){


        final FragmentManager fm = fragmentManager;
        final ArrayList<String> deportes = new ArrayList<String>();
        mDatabase.child("Preferencias").child(user.getUid()).addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Gson gson = new Gson();






                    for (DataSnapshot noticiaSnapshot: snapshot.getChildren()) {

                        deportes.add(noticiaSnapshot.getValue(String.class));

                        Log.e("Post",noticiaSnapshot.getValue(String.class));

                    }
                    getNoticias(fragmentManager, deportes, new ArrayList<Noticia>());




            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //Log.e("The read failed: " ,firebaseError.getMessage());
            }



        });

    }
}
