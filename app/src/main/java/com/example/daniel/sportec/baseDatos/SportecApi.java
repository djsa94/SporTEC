package com.example.daniel.sportec.baseDatos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daniel.sportec.R;
import com.example.daniel.sportec.deportes.DeportesFragment;
import com.example.daniel.sportec.modelos.Noticia;
import com.example.daniel.sportec.modelos.User;
import com.example.daniel.sportec.navigationMenu.NavigationMenu;
import com.example.daniel.sportec.noticias.NoticiasFragment;
import com.example.daniel.sportec.registro.RegistroActivity;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SportecApi{
    String url;
    RequestQueue queue;
    Gson gson;
    public SportecApi(Context contexto) {
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(contexto);
        url ="https://sportec.localtunnel.me";
        gson = new Gson();
    }

    public void getUser(String id, final String next, final FragmentManager fm){
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+"/users/" +id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("LLAMADA API",response.substring(0,500));
                        if(next == "news"){
                            Gson gson = new Gson();
                            Bundle bundle = new Bundle();
                            bundle.putString("user", response);
                            Fragment fragmentoNuevo = new NoticiasFragment();
                            fragmentoNuevo.setArguments(bundle);
                            fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LLAMADA API","ALGO PASO");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    public void registerUser(final User user, final Context contexto ){
        StringRequest postRequest = new StringRequest(Request.Method.POST, url+"/users",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        User userNuevo = gson.fromJson(response, User.class);
                        Log.d("GSONHECHO", userNuevo.getName()+userNuevo.getPassword()+userNuevo.get_id()+userNuevo.getEmail());
                        createLogin(userNuevo, contexto);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", user.getName());
                params.put("last_name", user.getApellido());
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());

                return params;
            }
        };
        queue.add(postRequest);
    }

    private void createLogin(final User user, final Context contexto){

        StringRequest postRequest = new StringRequest(Request.Method.POST, url+"/login",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Intent myIntent = new Intent(contexto, NavigationMenu.class);
                        myIntent.putExtra("user", response);
                        contexto.startActivity(myIntent);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("userId", user.get_id());
                params.put("password", user.getPassword());
                params.put("email", user.getEmail());

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void getDeportes(final User user, final FragmentManager fm){

        StringRequest postRequest = new StringRequest(Request.Method.GET, url+"/sport",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Gson gson = new Gson();
                        Bundle bundle = new Bundle();
                        bundle.putString("sports", response);
                        bundle.putString("user", gson.toJson(user));
                        Fragment fragmentoNuevo = new DeportesFragment();
                        fragmentoNuevo.setArguments(bundle);
                        fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("userId", user.get_id());
                params.put("password", user.getPassword());
                params.put("email", user.getEmail());

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void updateUserSports(final User user, final FragmentManager fm){
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url+"/users/sports/"+user.get_id(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Log.d("Response2", gson.toJson(user.getSports()));
                        Log.d("ResponseURL", url+"/users/"+user.get_id());

                            updateUserSportsAux(fm, user.getSports(), 0, user);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("sports", gson.toJson(user.getSports()));;

                return params;
            }

        };
        queue.add(postRequest);
    }

    public void updateUserSportsAux(final FragmentManager fm, final ArrayList<String> deportes,final int pos , final User user){
        if(pos == deportes.size()){
                        Gson gson = new Gson();
                        Bundle bundle = new Bundle();
                        bundle.putString("user", gson.toJson(user));
                        Fragment fragmentoNuevo = new NoticiasFragment();
                        fragmentoNuevo.setArguments(bundle);
                        fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();
        }else{
            StringRequest postRequest = new StringRequest(Request.Method.PUT, url+"/users/sports/"+user.get_id(),
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                            Log.d("Response2", gson.toJson(user.getSports()));
                            Log.d("ResponseURL", url+"/users/"+user.get_id());
//                            Gson gson = new Gson();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("user", response);
//                            Fragment fragmentoNuevo = new NoticiasFragment();
//                            fragmentoNuevo.setArguments(bundle);
//                            fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();
                            updateUserSportsAux(fm, deportes, pos+1, user);

                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.getMessage());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();

                    params.put("sports", gson.toJson(deportes.get(pos)));

                    return params;
                }

            };
            queue.add(postRequest);
        }
    }

    public void login(final String email, final String password, final Context contexto){
        StringRequest postRequest = new StringRequest(Request.Method.POST, url+"/login/"+email,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
//
                        User user = gson.fromJson(response, User.class);
                        getNoticias(user, contexto);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }


    public void getNoticias(final User user , final FragmentManager fm){
        StringRequest postRequest = new StringRequest(Request.Method.GET, url+"/news",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("getNoticias", response);

                        Gson gson = new Gson();
                        Bundle bundle = new Bundle();
                        bundle.putString("noticias", response);
                        bundle.putString("user", gson.toJson(user));
                        Fragment fragmentoNuevo = new NoticiasFragment();
                        fragmentoNuevo.setArguments(bundle);
                        fm.beginTransaction().replace(R.id.main_page, fragmentoNuevo).commit();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Gson gson = new Gson();
                Map<String, String>  params = new HashMap<String, String>();

                params.put("sports", gson.toJson(user.getSports()));

                return params;
            }
        };
        queue.add(postRequest);
    }
    public void getNoticias(final User user , final Context contexto){
        StringRequest postRequest = new StringRequest(Request.Method.GET, url+"/news",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("getNoticias", response);
                        Intent intent = new Intent(contexto, NavigationMenu.class);
                        intent.putExtra("noticias", response);
                        intent.putExtra("user", gson.toJson(user));
                        contexto.startActivity(intent);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Gson gson = new Gson();
                Map<String, String>  params = new HashMap<String, String>();

                params.put("sports", gson.toJson(user.getSports()));

                return params;
            }
        };
        queue.add(postRequest);
    }
    }










