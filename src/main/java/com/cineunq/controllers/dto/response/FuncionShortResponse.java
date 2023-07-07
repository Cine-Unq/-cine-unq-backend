package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Funcion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@AllArgsConstructor
public class FuncionShortResponse {

    @JsonIgnore
    private Funcion wrapped;

    public Long getId(){
        return wrapped.getId();
    }

    public String getHorario(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE(d/M) HH:mm", new Locale("es", "ARG"));
        String time = wrapped.getHoraInicio().format(formatter);
        return time.substring(0, 1).toUpperCase() + time.substring(1).toLowerCase();
    }
}
