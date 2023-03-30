package com.cineunq.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean estaOcupado;

    private String columna;

    private String fila;

    public Asiento(boolean estaOcupado, String columna, String fila) {
        this.estaOcupado = estaOcupado;
        this.columna = columna;
        this.fila = fila;
    }
}
