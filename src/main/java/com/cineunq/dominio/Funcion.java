package com.cineunq.dominio;

import com.cineunq.dominio.enums.EstadoAsiento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    public Pelicula peliculaEnFuncion;

    public LocalDateTime horaInicio;

    public LocalDateTime horaFin;

    //TODO ver de usar CascadeType.All y removeOrphan
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JsonIgnore
    public List<Asiento> asientosSala;

    @ManyToOne
    private Sala sala;

    @Builder
    public Funcion(Pelicula peliculaEnFuncion, LocalDateTime horaInicio,Sala sala,ArrayList<Asiento> asientos) {
        this.peliculaEnFuncion = peliculaEnFuncion;
        this.horaInicio = horaInicio;
        this.horaFin = peliculaEnFuncion!=null ? horaInicio.plusMinutes(peliculaEnFuncion.getDuracion()) : null;
        this.sala = sala;
        this.asientosSala = asientos.isEmpty() ? crearAsientosV2(sala) : asientos;
    }

    private List<Asiento> crearAsientosV2(Sala sala){
        List<Asiento> asientos = new ArrayList<>();
        List<FormaSala> forma = sala.getForma();
        for(FormaSala fs : forma){
            Asiento asiento = Asiento.builder().estado(EstadoAsiento.LIBRE).columna(fs.getColumnas()).fila(fs.getFila()).build();
            asientos.add(asiento);
        }
        return asientos;
    }

}
