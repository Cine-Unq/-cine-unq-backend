package com.cineunq.dominio;

import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private EstadoAsiento estado;

    @NonNull
    private String columna;

    @NonNull
    private String fila;

    @Builder
    public Asiento(EstadoAsiento estado,String columna,String fila) {
        this.estado = estado;
        this.columna = columna;
        this.fila = fila;
    }

    public void ocuparAsiento(){
        if(estado == EstadoAsiento.LIBRE){
            throw new MovieUnqLogicException("No se puede ocupar un asiento que no fue reservado");
        }
        if(estado == EstadoAsiento.OCUPADO){
            throw new MovieUnqLogicException("El asiento ya fue ocupado");
        }
        estado = EstadoAsiento.OCUPADO;
    }

    public void reservarAsiento(){
        if(estado == EstadoAsiento.OCUPADO){
            throw new MovieUnqLogicException("No se puede ocupar un asiento que ya fue ocupado");

        }
        if(estado == EstadoAsiento.RESERVADO){
            throw new MovieUnqLogicException("El asiento ya fue reservado");
        }
        estado = EstadoAsiento.RESERVADO;
    }

}
