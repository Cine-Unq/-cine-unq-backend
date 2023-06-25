package com.cineunq.controllers.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SalaAsientosRequest {

    private Integer rows;

    private Integer columns;

    private String name;

    private String tipoSala;

    private List<SalaFilaRequest> seats;

//    public List<List<SalaFilaRequest>> getAsientos(){
//        return matrix.stream().map(lista -> lista.stream().filter(SalaFilaRequest::getActive).toList()).toList();
//    }
}
