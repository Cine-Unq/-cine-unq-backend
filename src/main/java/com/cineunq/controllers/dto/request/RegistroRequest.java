package com.cineunq.controllers.dto.request;

import lombok.Data;

@Data
public class RegistroRequest {
    private String correo;
    private String password;
    private String nombre;
}
