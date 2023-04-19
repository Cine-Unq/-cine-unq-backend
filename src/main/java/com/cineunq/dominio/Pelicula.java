package com.cineunq.dominio;

import jakarta.persistence.*;
import lombok.*;

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

}
