package com.cineunq.dominio.builder;

import com.cineunq.dominio.Asiento;

public class AsientoBuilder {
    private boolean estaOcupado;
    private String columna;

    private String fila;

    public AsientoBuilder withEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
        return this;
    }

    public AsientoBuilder withNrColumna(String columna) {
        this.columna = columna;
        return this;
    }

    public AsientoBuilder withNrFila(String fila) {
        this.fila = fila;
        return this;
    }

    public Asiento build() {
        return new Asiento(estaOcupado, columna , fila);
    }
}