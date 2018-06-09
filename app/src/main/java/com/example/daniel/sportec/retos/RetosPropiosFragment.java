package com.example.daniel.sportec.retos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.equipos.EquipoVacioFragment;
import com.example.daniel.sportec.equipos.MiembrosEquipoAdapter;
import com.example.daniel.sportec.modelos.Reto;
import com.example.daniel.sportec.modelos.User;

import java.util.ArrayList;

public class RetosPropiosFragment extends Fragment {
    ArrayList<Reto> lista;
    public static RetosPropiosFragment newInstance() {
        RetosPropiosFragment fragmentoRetosPropios = new RetosPropiosFragment();
        return fragmentoRetosPropios;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.retos_propios_fragment, container, false);

        lista = new ArrayList<Reto>();
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Chest", "Search chest beetween 3 points"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));
        lista.add(new Reto("Kills", "Kill 3 people in snobby shores"));




        if(lista.isEmpty()){
            //buscar lista de miembros
        }else {

            RetosPropiosAdapter adapter = new RetosPropiosAdapter(getActivity(), lista);
            ListView list = (ListView) view.findViewById(R.id.retos_propios_listview);
            list.setAdapter(adapter);
            ajustarAlturaListView(list,adapter);
        }


        return view;
    }

    public static void ajustarAlturaListView (ListView listView, RetosPropiosAdapter adapter) {

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
