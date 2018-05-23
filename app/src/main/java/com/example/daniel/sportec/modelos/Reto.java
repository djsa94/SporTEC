package com.example.daniel.sportec.modelos;

public class Reto {
    String nombre;
    String descripcion;
    boolean fueCumplido;

    public Reto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Reto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFueCumplido() {
        return fueCumplido;
    }

    public void setFueCumplido(boolean fueCumplido) {
        this.fueCumplido = fueCumplido;
    }
}
