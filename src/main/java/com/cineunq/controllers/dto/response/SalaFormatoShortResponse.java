package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Sala;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalaFormatoShortResponse {

    @JsonIgnore
    private Sala wrapped;

    public Long getId(){
        return wrapped.getId();
    }

    public String getNombre(){
        return wrapped.getNombreSala();
    }

    public String getColumnas(){
        return wrapped.getColumnas();
    }

    public Integer getFilas(){
        return wrapped.getCantFilas();
    }
}
