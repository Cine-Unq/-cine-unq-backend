package com.cineunq.dominio.builder;

import com.cineunq.dominio.Asiento;

public class AsientoBuilder {
    private boolean estaOcupado;
    private String nrAsiento;

    public AsientoBuilder withEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
        return this;
    }

    public AsientoBuilder withNrAsiento(String nrAsiento) {
        this.nrAsiento = nrAsiento;
        return this;
    }

    public Asiento build() {
        return new Asiento(estaOcupado, nrAsiento);
    }
}