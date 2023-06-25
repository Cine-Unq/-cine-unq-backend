package com.cineunq.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FormaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String columnas;

    private String fila;

    @Builder
    public FormaSala(String columnas, String fila) {
        this.columnas = columnas;
        this.fila = fila;
    }
}
