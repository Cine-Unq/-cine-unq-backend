package com.cineunq.controllers.dto.request;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ActualizarAsientoRequest {
    private Long idCliente;
    private Long idCompra;
    private List<Long> asientos;
}
