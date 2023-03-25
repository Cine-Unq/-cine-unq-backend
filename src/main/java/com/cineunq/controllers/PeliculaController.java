package com.cineunq.controllers;

import com.cineunq.aspects.LogExecutionTime;
import com.cineunq.dominio.Pelicula;
import com.cineunq.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
@Tag(name = "Peliculas", description = "Endpoints para las peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Retorna todas las peliculas",
            description = "Todas las peliculas")
    @LogExecutionTime
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        List<Pelicula> users = peliculaService.getAll();
        return ResponseEntity.ok().body(users);
    }

}
