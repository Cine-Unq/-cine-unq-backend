package com.cineunq.controllers.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SaveCompraRequest {
    private Long idCliente;
    private Long idSala;
    private List<Long> idsAsientos;
}
