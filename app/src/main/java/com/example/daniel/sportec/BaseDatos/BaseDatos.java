package com.example.daniel.sportec.BaseDatos;

import android.util.Log;

import com.example.daniel.sportec.Objetos.Noticia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    private DatabaseReference mDatabase;

    public BaseDatos() {

    }
    public ArrayList<Noticia> getNoticias(String deporte){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Query listaNoticias = mDatabase.child("Deportes").child(deporte).child("Noticias").orderByChild("Fecha");
        final ArrayList<Noticia> noticias = new ArrayList<Noticia>();


        mDatabase.child("Deportes").child(deporte).child("Noticias").addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot noticiaSnapshot: snapshot.getChildren()) {

                Noticia noticia = noticiaSnapshot.getValue(Noticia.class);
                noticias.add(noticia);
                    Log.e("Post", noticia.getTitulo());
                    Log.e("Post", noticia.getContenido());
                    Log.e("Post", noticia.getFecha());
                }

            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //Log.e("The read failed: " ,firebaseError.getMessage());
            }
        });
        return noticias;
    }
}
