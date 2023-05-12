package com.cineunq.controllers.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String mail;
    private String password;
}
