package com.example.daniel.sportec.login;
import com.example.daniel.sportec.baseDatos.SportecApi;
import com.example.daniel.sportec.navigationMenu.NavigationMenu;
import com.example.daniel.sportec.registro.RegistroActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.sportec.R;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import java.util.Arrays;



public class FacebookLoginActivity extends AppCompatActivity{
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login_layout);

        setTheme(R.style.AppTheme);

//        //LoginManager.getInstance().logOut();
//        //Inicializa Firebase
//        mAuth = FirebaseAuth.getInstance();
//        //Callback Manager
//        callbackManager = CallbackManager.Factory.create();
//        //Boton de login creado y especifica permisos
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setReadPermissions(Arrays.asList(
//                "public_profile", "email", "user_birthday", "user_friends"));
//        //Hace callback
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });


        Button botonIngresar = (Button) findViewById(R.id.facebook_login_button_log_in);
        Button botonRegistrar = (Button) findViewById(R.id.facebook_login_button_register);
        final TextView email = (TextView) findViewById(R.id.facebook_login_text_view_username);
        final TextView pass = (TextView) findViewById(R.id.facebook_login_text_view_password);

        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUserCorreo(String.valueOf(email.getText()), String.valueOf(pass.getText()));
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
    private void crearUserCorreo(String user, String pass){
        mAuth.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //User registered successfully
                            Intent myIntent = new Intent(getApplicationContext(), NavigationMenu.class);
                            getApplicationContext().startActivity(myIntent);
                        }else{
                            if(mAuth.getCurrentUser() != null){
                                Intent myIntent = new Intent(getApplicationContext(), NavigationMenu.class);
                                getApplicationContext().startActivity(myIntent);
                            }else{
                                Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        //Autentica las credenciales en Firebase
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent myIntent = new Intent(getApplicationContext(), NavigationMenu.class);
                            getApplicationContext().startActivity(myIntent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Revisa si ya hay un user loggeado

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(FacebookLoginActivity.this, "Bienvenido" + currentUser.getDisplayName(),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NavigationMenu.class);
            startActivity(intent);
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken == null;


        //updateUI(currentUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
