package com.example.daniel.sportec.retos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.modelos.Equipo;
import com.example.daniel.sportec.modelos.Reto;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RetosEquiposAdapter extends ArrayAdapter<Equipo> {
    Context contexto;
    Gson gson = new Gson();

    public RetosEquiposAdapter(Context context, ArrayList<Equipo> equipos) {
        super(context, 0, equipos);
        this.contexto = context;
        Log.e("Probando lista", String.valueOf(equipos.size()));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.retos_equipos_item_layout, parent, false);
        }
        TextView nombre = (TextView) convertView.findViewById(R.id.retos_equipos_item_nombre_equipo);
        nombre.setText(getItem(position).getNombre());
        TextView puntos = (TextView) convertView.findViewById(R.id.retos_equipos_item_puntos_equipo);
        puntos.setText(String.valueOf(getItem(position).getPuntosTotales()));




        return convertView;
    }
}
