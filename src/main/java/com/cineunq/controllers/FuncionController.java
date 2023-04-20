package com.cineunq.controllers;

import com.cineunq.dominio.Funcion;
import com.cineunq.service.FuncionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcion")
@Tag(name = "Funciones", description = "Endpoints para las funciones")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping(value = "/{pelicula}")
    public List<Funcion> funcionesPorPelicula(@PathVariable("pelicula") String pelicula){
        return funcionService.funcionesPorPelicula(pelicula);
    }
}
