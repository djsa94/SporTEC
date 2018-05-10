package com.example.daniel.sportec.Objetos;

import android.media.Image;


public class Noticia {
    private String Titulo;
    private String Contenido;
    private String Imagen;
    private String Fecha;

    public Noticia() {

    }

    public Noticia(String titulo, String contenido, String imagen, String fecha) {
        this.Titulo = titulo;
        this.Contenido = contenido;
        this.Imagen = imagen;
        this.Fecha = fecha;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        this.Contenido = contenido;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }
}
