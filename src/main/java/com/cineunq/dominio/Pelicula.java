package com.cineunq.dominio;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Integer duracion;

    private String imagen;

    @OneToMany
    private List<Asiento> asientos;

    public Pelicula() {
    }

    public Pelicula(String nombre, String descripcion, Integer duracion, String imagen, List<Asiento> asientos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.imagen = imagen;
        this.asientos = asientos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public String getImagen() {
        return imagen;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }
}
