package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Integer duracion;

    private String imagen;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Asiento> asientos;

    public Pelicula(String nombre, String descripcion, Integer duracion, String imagen, List<Asiento> asientos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.imagen = imagen;
        this.asientos = asientos;
    }
}
