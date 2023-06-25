package com.cineunq.controllers.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostFuncionRequest {

    private String pelicula;

    private String sala;

    private LocalDateTime horaInicio;
}
