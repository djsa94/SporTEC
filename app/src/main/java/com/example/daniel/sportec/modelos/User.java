package com.example.daniel.sportec.modelos;

import java.util.ArrayList;

public class User {
    String name;
    String apellido;
    String email;
    String password;
    String _id;
    ArrayList<String> sports;


    public User(String name, String apellido, String email, String _id, String password, ArrayList<String> sports) {
        this.name = name;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this._id = _id;
        this.sports = sports;
    }

    public User() {
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getSports() {
        return sports;
    }

    public void setSports(ArrayList<String> sports) {
        this.sports = sports;
    }
}
