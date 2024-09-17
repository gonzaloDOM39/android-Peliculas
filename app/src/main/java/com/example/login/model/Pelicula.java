package com.example.login.model;

public class Pelicula {
    private String titulo;
    private String descripcion;
    private String imagenUrl;

    public Pelicula(String titulo, String descripcion, String imagenUrl) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }
}
