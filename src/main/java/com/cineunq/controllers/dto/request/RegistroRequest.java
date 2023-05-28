package com.cineunq.controllers.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistroRequest {
    @NotBlank
    private String correo;
    @NotBlank
    private String password;
    @NotBlank
    private String nombre;
}
