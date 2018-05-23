package com.example.daniel.sportec.Noticias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.sportec.BaseDatos.BaseDatos;
import com.example.daniel.sportec.Deportes.DeportesFragment;
import com.example.daniel.sportec.FacebookLogin.FacebookLoginActivity;
import com.example.daniel.sportec.Objetos.Noticia;
import com.example.daniel.sportec.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class NoticiasFragment extends Fragment  {
    ArrayList<Noticia> lista;
    Gson gson = new Gson();
    BaseDatos db = new BaseDatos();

    public static NoticiasFragment newInstance() {
        NoticiasFragment fragmentoNoticias = new NoticiasFragment();


        return fragmentoNoticias;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.noticias_feed_layout, container, false);

        TypeToken<ArrayList<Noticia>> token = new TypeToken<ArrayList<Noticia>>() {};
        lista = gson.fromJson(getArguments().getString("Noticias"), token.getType());


        if(lista.isEmpty()){
            db.getDeportes(getFragmentManager(), FirebaseAuth.getInstance().getCurrentUser());
        }else {
            ImageView imagen = (ImageView) view.findViewById(R.id.noticias_feed_layout_featured_image_view);
            TextView titulo = (TextView) view.findViewById((R.id.noticias_feed_layout_featured_titulo));

            TextView fecha = (TextView) view.findViewById(R.id.noticias_feed_layout_featured_fecha);

            final Noticia noticia = lista.get(0);
            titulo.setText(noticia.getTitulo());
            ;
            fecha.setText(noticia.getFecha());

            RelativeLayout layoutFeatured = (RelativeLayout) view.findViewById(R.id.noticias_feed_layout_featured);
            layoutFeatured.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragmento;
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmento = new NoticiaDetalleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("noticia", gson.toJson(noticia));
                    fragmento.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main_page, fragmento);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            });


            db.getImagen(noticia.getImagen(), imagen);

            lista.remove(0);
            NoticiasAdapter adapter = new NoticiasAdapter(getActivity(), lista);
            ListView list = (ListView) view.findViewById(R.id.noticias_feed_layout_list_view);
            list.setAdapter(adapter);
        }







        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }





}
