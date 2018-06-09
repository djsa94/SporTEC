package com.example.daniel.sportec.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.baseDatos.SportecApi;
import com.example.daniel.sportec.registro.RegistroActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login_layout);
        setTheme(R.style.AppTheme);
        Button botonIngresar = (Button) findViewById(R.id.facebook_login_button_log_in);
        Button botonRegistrar = (Button) findViewById(R.id.facebook_login_button_register);
        final TextView email = (TextView) findViewById(R.id.facebook_login_text_view_username);
        final TextView pass = (TextView) findViewById(R.id.facebook_login_text_view_password);

        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SportecApi api = new SportecApi(getApplicationContext());
                api.login(email.getText().toString(), pass.getText().toString(), getApplicationContext());
            }

        });
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
