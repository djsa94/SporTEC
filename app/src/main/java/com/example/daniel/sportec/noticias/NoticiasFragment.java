package com.example.daniel.sportec.noticias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.daniel.sportec.baseDatos.BaseDatos;
import com.example.daniel.sportec.equipos.EquiposFragment;
import com.example.daniel.sportec.modelos.Noticia;
import com.example.daniel.sportec.R;
import com.example.daniel.sportec.retos.RetosFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

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

                    //fragmento = new NoticiaDetalleFragment();
                    fragmento = new RetosFragment();
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
            ajustarAlturaListView(list,adapter);
        }







        return view;
    }

    public static void ajustarAlturaListView (ListView listView, NoticiasAdapter adapter) {

        //ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }





}
