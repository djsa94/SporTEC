package com.example.daniel.sportec.retos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.modelos.Equipo;

import java.util.ArrayList;

public class RetosEquiposFragment extends Fragment {
    ArrayList<Equipo> lista;

    public static RetosEquiposFragment newInstance() {
        RetosEquiposFragment fragmentoRetosEquipos = new RetosEquiposFragment();
        return fragmentoRetosEquipos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.retos_equipos_fragment, container, false);

        lista = new ArrayList<Equipo>();
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));
        lista.add(new Equipo("futbol", "allblacks"));


        if (lista.isEmpty()) {
            //buscar lista de miembros
        } else {

            RetosEquiposAdapter adapter = new RetosEquiposAdapter(getActivity(), lista);
            ListView list = (ListView) view.findViewById(R.id.retos_equipos_listview);
            list.setAdapter(adapter);
            ajustarAlturaListView(list, adapter);
        }

        return view;
    }

    public static void ajustarAlturaListView(ListView listView, RetosEquiposAdapter adapter) {

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
