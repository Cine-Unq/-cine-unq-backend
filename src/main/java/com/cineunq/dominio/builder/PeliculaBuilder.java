package com.cineunq.dominio.builder;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Pelicula;

import java.util.List;

public class PeliculaBuilder {
    private String nombre;
    private String descripcion;
    private Integer duracion;
    private String imagen;
    private List<Asiento> asientos;

    public PeliculaBuilder withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PeliculaBuilder withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public PeliculaBuilder withDuracion(Integer duracion) {
        this.duracion = duracion;
        return this;
    }

    public PeliculaBuilder withImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    public PeliculaBuilder withAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
        return this;
    }

    public Pelicula build() {
        return new Pelicula(nombre, descripcion, duracion, imagen, asientos);
    }
}