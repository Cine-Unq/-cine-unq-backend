package com.cineunq.controllers.dto.response;

import lombok.Getter;
import lombok.Setter;

//Esta clase va a ser la que nos devolverá la información con el token y el tipo que tenga este
@Getter
@Setter
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
