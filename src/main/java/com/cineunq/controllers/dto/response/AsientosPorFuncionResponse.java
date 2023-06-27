package com.cineunq.controllers.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AsientosPorFuncionResponse {

    public String cantColumnas ;

    public Integer cantFilas;

    public List<AsientoResponse> asientos;

    public List<AsientoResponse> getAsientos(){
        return asientos;
    }
}
