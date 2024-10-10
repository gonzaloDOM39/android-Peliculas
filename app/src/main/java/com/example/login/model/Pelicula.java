package com.example.login.model;

import java.io.Serializable;

public class Pelicula implements Serializable {
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
