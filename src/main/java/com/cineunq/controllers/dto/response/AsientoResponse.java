package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AsientoResponse {

    @NonNull
    private Asiento wrapped;

    @JsonIgnore
    private String letters = "abcdefghijklmnñopqrstuvwxyz";

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

    public String getLetter(){
        return String.valueOf(letters.charAt(Integer.parseInt(wrapped.getFila()))).toUpperCase();
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
