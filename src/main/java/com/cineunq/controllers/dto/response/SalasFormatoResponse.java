package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Sala;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SalasFormatoResponse {

    private String tipo;

    @JsonIgnore
    private List<Sala> salasDisponibles;

    public List<SalaFormatoShortResponse> getSalas(){
        return salasDisponibles.stream().map(SalaFormatoShortResponse::new).toList();
    }
}
