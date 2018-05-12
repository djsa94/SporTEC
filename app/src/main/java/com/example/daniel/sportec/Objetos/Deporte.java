package com.example.daniel.sportec.Objetos;

public class Deporte {
    private String Nombre;
    private String Imagen;

    public Deporte(String nombre, String imagen) {
        this.Nombre = nombre;
        this.Imagen = imagen;
    }

    public Deporte() {
    }

    public String getNombre() {

        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
}
