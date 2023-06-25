package com.cineunq.controllers.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AsientosPorFuncionResponse {

    public String columnas ;

    public Integer filas;

    public List<AsientoResponse> asientos;

    public  String getColumnas(){
        return columnas;
    }

    public Integer getFilas(){
        return filas;
    }

    public List<AsientoResponse> getAsientos(){
        return asientos;
    }
}
