package com.cineunq.controllers;

import com.cineunq.controllers.dto.response.FuncionPorPeliculaResponse;
import com.cineunq.dominio.Funcion;
import com.cineunq.service.FuncionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcion")
@Tag(name = "Funciones", description = "Endpoints para las funciones")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping(value = "/{pelicula}")
    public List<FuncionPorPeliculaResponse> funcionesPorPelicula(@PathVariable("pelicula") String pelicula){
        Map<String,List<Funcion>> funciones = funcionService.funcionesPorPelicula(Long.valueOf(pelicula));
        return funciones.entrySet().stream().map(stringListEntry -> new FuncionPorPeliculaResponse(stringListEntry.getKey(),stringListEntry.getValue())).toList();
    }
}
