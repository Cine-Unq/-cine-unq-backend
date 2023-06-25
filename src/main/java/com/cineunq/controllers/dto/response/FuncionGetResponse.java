package com.cineunq.controllers.dto.response;

import com.cineunq.dominio.Funcion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class FuncionGetResponse {

    @JsonIgnore
    private Funcion wrapped;

    public String getId(){
        return wrapped.getId().toString();
    }

    public String getPeliculaEnFuncion(){
        return wrapped.getPeliculaEnFuncion().getNombre();
    }

    public String getHorario(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return formatter.format(wrapped.horaInicio);
        //return wrapped.getHoraInicio().getHour() + "-" + wrapped.getHoraInicio().getMinute();
    }

    public String getSala(){
        return wrapped.getSala().getNombreSala();
    }

    public String getTipoSala(){
        return wrapped.getSala().getTipoSala().getTipoSala().nombre();
    }
}
