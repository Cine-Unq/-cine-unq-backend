package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Funcion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;


@AllArgsConstructor
public class FuncionShortResponse {

    @JsonIgnore
    private Funcion wrapped;

    public Long getId(){
        return wrapped.getId();
    }

    public String getHorario(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(wrapped.horaInicio);
        //return wrapped.getHoraInicio().getHour() + "-" + wrapped.getHoraInicio().getMinute();
    }
}
