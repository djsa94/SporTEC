package com.example.daniel.sportec.navigationMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.sportec.baseDatos.BaseDatos;
import com.example.daniel.sportec.login.FacebookLoginActivity;
import com.example.daniel.sportec.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BaseDatos db = new BaseDatos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_slice_activity);
        setTheme(R.style.AppTheme);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView username = (TextView) hView.findViewById(R.id.menu_slice_nav_header_user);
        username.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        navigationView.setNavigationItemSelectedListener(this);

        ;

        db.getPreferencias(getSupportFragmentManager(), FirebaseAuth.getInstance().getCurrentUser());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slice_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




        if (id == R.id.activity_menu_slice_drawer_noticias) {
            //db.getNoticias(getSupportFragmentManager(), new ArrayList<String>(), new ArrayList<Noticia>());
            db.getPreferencias(getSupportFragmentManager(), FirebaseAuth.getInstance().getCurrentUser());

        }

        else if (id == R.id.menu_slice_drawer_login) {
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
            Toast.makeText(NavigationMenu.this, "Sesion Cerrada",
                    Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getApplicationContext(), FacebookLoginActivity.class);
            getApplicationContext().startActivity(myIntent);
        }

        else if(id == R.id.activity_menu_slice_drawer_perfil_usuario) {
            //fm.beginTransaction().replace(R.id.main_page, new busqueda_voluntarios_Fragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
