package com.cineunq.controllers;

import com.cineunq.aspects.LogExecutionTime;
import com.cineunq.dominio.Pelicula;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
@Tag(name = "Peliculas", description = "Endpoints para las peliculas")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna todas las peliculas",description = "Todas las peliculas")
    public List<Pelicula> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAll();
        return peliculas;
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    @Operation(summary = "Retorna una pelicula",description = "Devuelve una pelicula si existe")
    public Pelicula getPeliculaByID(@PathVariable("id") String id) throws NotFoundException {
        Pelicula pelicula = peliculaService.findByID(Long.parseLong(id));
        return pelicula;
    }
}
