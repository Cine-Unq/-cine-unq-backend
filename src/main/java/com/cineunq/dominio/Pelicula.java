package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String descripcion;

    @NonNull
    private Integer duracion;

    @NonNull
    private String imagen;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JsonIgnore
    @NonNull
    private List<Asiento> asientos;

//    public Pelicula(String nombre, String descripcion, Integer duracion, String imagen, List<Asiento> asientos) {
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.duracion = duracion;
//        this.imagen = imagen;
//        this.asientos = asientos;
//    }
}
