package com.example.daniel.sportec.baseDatos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;

import com.example.daniel.sportec.deportes.DeportesFragment;
import com.example.daniel.sportec.noticias.NoticiasFragment;
import com.example.daniel.sportec.modelos.Deporte;
import com.example.daniel.sportec.modelos.Noticia;
import com.example.daniel.sportec.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BaseDatos {

    private DatabaseReference mDatabase;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ArrayList<String> deportes;
    public BaseDatos() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        deportes = new ArrayList<String>();
    }


    public void ingresarPreferencias(){

    for(int i = 0; i<deportes.size(); i++) {

        mDatabase.child("Preferencias").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Deporte" + String.valueOf(i)).setValue(deportes.get(i));
    }




    }
    public void agregarPreferencias(Deporte deporte){
        //deportes.add(deporte.getNombre());


    }
    public void getImagen(String id, ImageView view){
        StorageReference storageRef = storage.getReference();
        final ImageView view1 = view;
        Log.e("ENTRADA IMAGEN", id);
        StorageReference imageRef = storageRef.child(id);

        final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                view1.setImageBitmap(bmp);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
    public void getNoticias(FragmentManager fragmentManager, ArrayList<String> deportesIn, ArrayList<Noticia> noticiasIn, int positionIn){
        final ArrayList<String> deportes = deportesIn;
        final ArrayList<Noticia> noticias = noticiasIn;
        final int position = positionIn;
        final FragmentManager fm = fragmentManager;

        if(position == deportes.size() ){
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("Noticias", gson.toJson(noticias));
            Fragment fragmentoNuevo = new NoticiasFragment();
            fragmentoNuevo.setArguments(bundle);
            fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();
        }else{
            mDatabase.child("Deportes").child(deportes.get(position)).child("Noticias").addValueEventListener(new ValueEventListener() {



                @Override
                public void onDataChange(DataSnapshot snapshot) {





                    for (DataSnapshot noticiaSnapshot: snapshot.getChildren()) {

                        Noticia noticia = noticiaSnapshot.getValue(Noticia.class);
                        noticias.add(noticia);
                        Log.e("Post", noticia.getTitulo());
                        Log.e("Post", noticia.getContenido());
                        Log.e("Post", noticia.getFecha());
                        Log.e("Post", noticia.getImagen());
                    }

                    getNoticias(fm, deportes, noticias, position+1);



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
                    getNoticias(fragmentManager, deportes, new ArrayList<Noticia>(), 0);




            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //Log.e("The read failed: " ,firebaseError.getMessage());
            }



        });

    }
    public void getDeportes(final FragmentManager fragmentManager, FirebaseUser user ){


        final FragmentManager fm = fragmentManager;
        final ArrayList<Deporte> deportes = new ArrayList<Deporte>();
        mDatabase.child("Deportes").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Gson gson = new Gson();

                for (DataSnapshot deportesSnapshot: snapshot.getChildren()) {

                    deportes.add(deportesSnapshot.getValue(Deporte.class));

                    //Log.e("Post",deportesSnapshot.getValue(String.class));

                }
                Fragment fragmento;
                FragmentManager fragmentManager = fm;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                fragmento = new DeportesFragment();

                Bundle arguments = new Bundle();
                arguments.putString( "deportes" , gson.toJson(deportes));
                fragmento.setArguments(arguments);
                fragmentTransaction.replace(R.id.main_page, fragmento);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();




            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //Log.e("The read failed: " ,firebaseError.getMessage());
            }



        });

    }
}
