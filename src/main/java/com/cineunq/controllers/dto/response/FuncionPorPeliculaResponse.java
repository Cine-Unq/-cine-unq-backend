package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Funcion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FuncionPorPeliculaResponse {

    @JsonIgnore
    private String tipoSala;
    @JsonIgnore
    private List<Funcion> funciones;

    public String getTipo(){
        return  tipoSala;
    }

    public List<FuncionShortResponse> getHorarios(){
        return funciones.stream().map(FuncionShortResponse::new).toList();
    }

}
