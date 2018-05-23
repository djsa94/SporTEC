package com.example.daniel.sportec.modelos;

import java.util.ArrayList;

public class Equipo {
    String deporte;
    String nombre;
    ArrayList<String> integrantes;
    ArrayList<Reto> retos;

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<String> integrantes) {
        this.integrantes = integrantes;
    }

    public ArrayList<Reto> getRetos() {
        return retos;
    }

    public void setRetos(ArrayList<Reto> retos) {
        this.retos = retos;
    }
}
