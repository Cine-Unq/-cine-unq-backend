package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Compra;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class CompraResponse {

    private Compra wrapped;

    public String getPelicula(){
        return wrapped.getSala().getFuncion().getPeliculaEnFuncion().getNombre();
    }

    public String getCliente(){
        return wrapped.getClienteCompra().getCorreo();
    }

    public List<Long> getAsientos(){
        return wrapped.getSala().getAsientosSala().stream().map(Asiento::getId).toList();
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getFechaCompra(){
        return wrapped.getFechaCompra();
    }

}
