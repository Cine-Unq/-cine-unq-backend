package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AsientoResponse {
    private Asiento wrapped;

    public String getEstado() {
        switch (wrapped.getEstado()) {
            case LIBRE:
                return "LIBRE";
            case RESERVADO:
                return "RESERVADO";
            case OCUPADO:
                return "OCUPADO";
            default:
                return "";
        }
    }

    public Integer getPosColumna() {
        return Integer.valueOf(wrapped.getColumna());
    }

    public Integer getPosFila(){
        return Integer.valueOf(wrapped.getFila());
    }

    public Long getId(){
        return wrapped.getId();
    }

}
