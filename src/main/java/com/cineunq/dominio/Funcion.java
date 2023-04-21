package com.cineunq.dominio;

import com.cineunq.dominio.builder.AsientoBuilder;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public LocalDateTime horaInicio;

    public LocalDateTime horaFin;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JsonIgnore
    public List<Asiento> asientosSala;

    @Builder
    public Funcion(Pelicula peliculaEnFuncion, LocalDateTime horaInicio,Sala sala) {
        this.peliculaEnFuncion = peliculaEnFuncion;
        this.horaInicio = horaInicio;
        this.horaFin = horaInicio.plusMinutes(peliculaEnFuncion.getDuracion());
        crearAsientos(sala);
    }

    private void crearAsientos(Sala sala){
        List<Asiento> asientos = new ArrayList<>();
        for (int i = 0; i < sala.getColumnas().length();i++){ //Para las Columnas
            for(int j = 1; j < sala.getCantFilas();j++){ //Para las filas
                Asiento a = new AsientoBuilder().withEstaOcupado(EstadoAsiento.LIBRE).withNrColumna(Character.toString(sala.getColumnas().charAt(i))).withNrFila(Integer.toString(j)).build();
                asientos.add(a);
            }
        }
        asientosSala = asientos;
    }
}
