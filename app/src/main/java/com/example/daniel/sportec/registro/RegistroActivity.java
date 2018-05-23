package com.example.daniel.sportec.registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.login.FacebookLoginActivity;

public class RegistroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        setTheme(R.style.AppTheme);
        EditText nombre = (EditText) findViewById(R.id.registro_activity_entrada_nombre);
        EditText apellido = (EditText) findViewById(R.id.registro_activity_entrada_apellido);
        EditText email = (EditText) findViewById(R.id.registro_activity_entrada_email);

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

            }
        });


        Intent intent = new Intent(this, FacebookLoginActivity.class);
        startActivity(intent);
    }
}
