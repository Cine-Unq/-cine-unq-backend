package com.cineunq.controllers.dto;

import com.cineunq.dominio.enums.EstadoAsiento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ActualizarAsientoDto {
    private Long id;
    private EstadoAsiento estaOcupado;
}
