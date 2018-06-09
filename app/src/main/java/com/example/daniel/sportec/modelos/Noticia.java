package com.example.daniel.sportec.modelos;


public class Noticia {
    private String titulo;
    private String contenido;
    private String imagen;
    private String fecha;

    public Noticia() {

    }

    public Noticia(String titulo, String contenido, String imagen, String fecha) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.imagen = imagen;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
