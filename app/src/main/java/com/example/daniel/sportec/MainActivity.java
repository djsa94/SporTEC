package com.example.daniel.sportec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.sportec.BaseDatos.BaseDatos;
import com.example.daniel.sportec.FacebookLogin.FacebookLoginActivity;
import com.example.daniel.sportec.NavigationMenu.NavigationMenu;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);
        //LoginManager.getInstance().logOut();

        Intent intent = new Intent(this, FacebookLoginActivity.class);
        startActivity(intent);
    }
}
