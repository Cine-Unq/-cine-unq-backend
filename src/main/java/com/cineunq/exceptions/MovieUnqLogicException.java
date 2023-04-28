package com.cineunq.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieUnqLogicException extends RuntimeException {

    public MovieUnqLogicException(String message) {
        super(message);
    }

    public MovieUnqLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
