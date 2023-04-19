package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    public Pelicula peliculaEnFuncion;

    public LocalDate horaInicio;

    public LocalDate horaFin;

    @Builder
    public Funcion(Pelicula peliculaEnFuncion, LocalDate horaInicio, LocalDate horaFin) {
        this.peliculaEnFuncion = peliculaEnFuncion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
