package com.cineunq.dominio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NonNull
    private Integer duracion;

    @NonNull
    private String imagen;

    @Builder
    public Pelicula(@NonNull String nombre, @NonNull String descripcion, @NonNull Integer duracion, @NonNull String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.imagen = imagen;
    }
}
