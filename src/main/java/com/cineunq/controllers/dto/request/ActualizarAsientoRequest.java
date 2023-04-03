package com.cineunq.controllers.dto.request;

import com.cineunq.dominio.enums.EstadoAsiento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ActualizarAsientoRequest {
    private Long id;
    private EstadoAsiento estaOcupado;
}
