package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AsientoResponse {
    private Asiento wrapped;

    public boolean getEstaLibre(){
        return wrapped.getEstado() == EstadoAsiento.LIBRE;
    }

    public boolean getEstaReservado(){
        return wrapped.getEstado() == EstadoAsiento.RESERVADO;
    }

    public boolean getEstaOcupado(){
        return wrapped.getEstado() == EstadoAsiento.OCUPADO;
    }

    public String getColumna() {
        return wrapped.getColumna();
    }

    public String getFila(){
        return wrapped.getFila();
    }

    public Long getId(){
        return wrapped.getId();
    }

}
