package com.cineunq.controllers.dto.request;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Cliente;
import com.cineunq.dominio.Pelicula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SaveCompraRequest {
    private Cliente cliente;
    private List<Asiento> asientosComprados;
    private Pelicula pelicula;
}
