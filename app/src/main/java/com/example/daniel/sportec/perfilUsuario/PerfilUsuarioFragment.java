package com.example.daniel.sportec.perfilUsuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniel.sportec.R;

public class PerfilUsuarioFragment extends Fragment {

    public static PerfilUsuarioFragment newInstance() {
        PerfilUsuarioFragment fragmentoPerfil = new PerfilUsuarioFragment();


        return fragmentoPerfil;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.perfil_usuario_layout, container, false);

        EditText nombre = (EditText) view.findViewById(R.id.perfil_usuario_entrada_nombre);
        EditText apellido = (EditText) view.findViewById(R.id.perfil_usuario_entrada_apellido);
        EditText email = (EditText) view.findViewById(R.id.perfil_usuario_entrada_email);

        Button botonCambiarFoto = (Button) view.findViewById(R.id.perfil_usuario_boton_cambiar_foto);
        Button botonEditarDeportes = (Button) view.findViewById(R.id.perfil_usuario_boton_editar_deportes);
        Button botonGuardarCambios = (Button) view.findViewById(R.id.perfil_usuario_boton_guardar_cambios);

        botonEditarDeportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botonGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botonCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });









        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
