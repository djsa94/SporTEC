package com.example.daniel.sportec.Deportes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.daniel.sportec.BaseDatos.BaseDatos;
import com.example.daniel.sportec.Objetos.Deporte;
import com.example.daniel.sportec.R;

import java.util.ArrayList;

public class DeportesAdapter extends BaseAdapter{
    private final Context contexto;
    private final ArrayList<Deporte> deportes;
    private final BaseDatos db;

    // 1
    public DeportesAdapter(Context context, ArrayList<Deporte> deportesIn, BaseDatos db) {
        this.contexto = context;
        this.deportes = deportesIn;
        this.db = db;

    }

    // 2
    @Override
    public int getCount() {
        return deportes.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(contexto).inflate(R.layout.deportes_agregar_item_layout, parent, false);
        }

        ImageView imagen = (ImageView) convertView.findViewById(R.id.deportes_agregar_item_imagen);
        TextView nombre = (TextView) convertView.findViewById(R.id.deportes_agregar_item_nombre);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.deportes_agregar_icon_layout);

        db.getImagen(deportes.get(position).getImagen(), imagen);
        nombre.setText(deportes.get(position).getNombre());

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.agregarPreferencias(deportes.get(position));
            }
        });



        return convertView;
    }
}
