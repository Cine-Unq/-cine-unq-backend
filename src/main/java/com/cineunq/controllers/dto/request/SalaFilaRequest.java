package com.cineunq.controllers.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SalaFilaRequest { //Representa a un asiento

    private String fila;

    private String columna;
}
