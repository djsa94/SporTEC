package com.example.daniel.sportec.equipos;

import android.content.Context;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.baseDatos.BaseDatos;
import com.example.daniel.sportec.modelos.Noticia;
import com.example.daniel.sportec.modelos.User;
import com.example.daniel.sportec.noticias.NoticiaDetalleFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MiembrosEquipoAdapter extends ArrayAdapter<User>{

    Context contexto;
    Gson gson = new Gson();

    public MiembrosEquipoAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
        this.contexto = context;
        Log.e("Probando lista", String.valueOf(users.size()));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.equipo_fragment_miembro_layout, parent, false);
        }
        TextView nombre = (TextView) convertView.findViewById(R.id.equipo_fragment_miembro_nombre);
        nombre.setText(getItem(position).getNombre() + " " + getItem(position).getApellido());


        return convertView;
    }
}
