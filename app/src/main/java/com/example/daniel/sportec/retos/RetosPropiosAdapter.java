package com.example.daniel.sportec.retos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.modelos.Reto;
import com.example.daniel.sportec.modelos.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RetosPropiosAdapter extends ArrayAdapter<Reto> {
    Context contexto;
    Gson gson = new Gson();

    public RetosPropiosAdapter(Context context, ArrayList<Reto> retos) {
        super(context, 0, retos);
        this.contexto = context;
        Log.e("Probando lista", String.valueOf(retos.size()));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.retos_propios_item_layout, parent, false);
        }
        TextView nombre = (TextView) convertView.findViewById(R.id.retos_propios_item_nombre);
        nombre.setText(getItem(position).getNombre());




        return convertView;
    }
}
