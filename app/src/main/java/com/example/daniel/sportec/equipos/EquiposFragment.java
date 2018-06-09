package com.example.daniel.sportec.equipos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.modelos.User;

import java.util.ArrayList;

public class EquiposFragment extends Fragment {
    ArrayList<User> lista;

    public static EquiposFragment newInstance() {
        EquiposFragment fragmentoEquipos = new EquiposFragment();
        return fragmentoEquipos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.equipo_fragment_layout, container, false);
        TextView tvNombre = (TextView) view.findViewById(R.id.equipo_fragment_tv_nombre_equipo);
        TextView tvInfo = (TextView) view.findViewById(R.id.equipo_fragment_tv_descripcion);
        tvNombre.setText("Familia");
        tvInfo.setText("Este es un grupo de prueba utilizado para probar las pruebas que tenemos que probar-CL9");

        if(lista.isEmpty()){
            //buscar lista de miembros
        }else {

            MiembrosEquipoAdapter adapter = new MiembrosEquipoAdapter(getActivity(), lista);
            ListView list = (ListView) view.findViewById(R.id.equipo_fragment_listview_miembros);
            list.setAdapter(adapter);
            ajustarAlturaListView(list,adapter);
        }
        return view;
    }

    public static void ajustarAlturaListView (ListView listView, MiembrosEquipoAdapter adapter) {
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
