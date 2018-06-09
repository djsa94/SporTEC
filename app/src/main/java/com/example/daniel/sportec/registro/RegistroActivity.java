package com.example.daniel.sportec.registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.baseDatos.SportecApi;
import com.example.daniel.sportec.login.FacebookLoginActivity;
import com.example.daniel.sportec.modelos.User;

public class RegistroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        setTheme(R.style.AppTheme);
        final TextView nombre = (TextView) findViewById(R.id.registro_activity_entrada_nombre);
        final TextView apellido = (TextView) findViewById(R.id.registro_activity_entrada_apellido);
        final TextView email = (TextView) findViewById(R.id.registro_activity_entrada_email);
        final TextView password = (TextView) findViewById(R.id.registro_activity_entrada_password);

        Button botonAgregarFoto = (Button) findViewById(R.id.registro_activity_boton_agregar_foto);
        Button botonRegistrarse = (Button) findViewById(R.id.registro_activity_boton_registrarse);

        botonAgregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = "" + String.valueOf(password.getText());
                User userNuevo = new User();
                userNuevo.setName(nombre.getText().toString());
                userNuevo.setApellido(apellido.getText().toString());
                userNuevo.setEmail(email.getText().toString());
                userNuevo.setPassword(pass);

                new SportecApi(getApplicationContext()).registerUser(userNuevo, getApplicationContext());


            }
        });



    }
}
