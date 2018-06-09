package com.example.daniel.sportec.deportes;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.daniel.sportec.baseDatos.BaseDatos;
import com.example.daniel.sportec.modelos.Deporte;
import com.example.daniel.sportec.R;
import com.example.daniel.sportec.modelos.User;

import java.util.ArrayList;

public class DeportesAdapter extends BaseAdapter{
    private final Context contexto;
    private final ArrayList<Deporte> deportes;
    private final User user;

    // 1
    public DeportesAdapter(Context context, ArrayList<Deporte> deportesIn, User user) {
        this.contexto = context;
        this.deportes = deportesIn;
        this.user = user;

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

        final ImageView imagen = (ImageView) convertView.findViewById(R.id.deportes_agregar_item_imagen);
        TextView nombre = (TextView) convertView.findViewById(R.id.deportes_agregar_item_nombre);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.deportes_agregar_icon_layout);

       // db.getImagen(deportes.get(position).getImagen(), imagen);
        nombre.setText(deportes.get(position).getName());

        imagen.setOnClickListener(new View.OnClickListener() {
            boolean selected = false;
            @Override
            public void onClick(View view) {
                //db.agregarPreferencias(deportes.get(position));

                if(selected){
                    imagen.setColorFilter(Color.argb(0, 0, 0, 0));
                    for(int i = 0; i<user.getSports().size(); i++){
                        if(user.getSports().get(i) == deportes.get(position).getName()){
                            user.getSports().remove(i);
                        }
                    }
                    Log.d("FLAGBORRADO", String.valueOf(user.getSports().size()) );
                    selected = false;
                }else{
                    imagen.setColorFilter(Color.argb(100, 0, 0, 0));
                    user.getSports().add(deportes.get(position).getName());
                    selected = true;
                    Log.d("FLAGAGREGADO", String.valueOf(user.getSports().size()) );
                }
            }
        });



        return convertView;
    }
}
