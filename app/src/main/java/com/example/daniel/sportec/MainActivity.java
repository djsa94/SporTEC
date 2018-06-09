package com.example.daniel.sportec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.daniel.sportec.login.FacebookLoginActivity;
import com.example.daniel.sportec.login.LoginActivity;
import com.example.daniel.sportec.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);
        //LoginManager.getInstance().logOut();




        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
