package com.cineunq.controllers;

import com.cineunq.aspects.LogExecutionTime;
import com.cineunq.dominio.Pelicula;
import com.cineunq.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/peliculas")
@Tag(name = "Peliculas", description = "Endpoints para las peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna todas las peliculas",description = "Todas las peliculas")
    @LogExecutionTime
    public ResponseEntity<List<Pelicula>> getAllPeliculas(@PathVariable String pelicula) {
        List<Pelicula> peliculas = peliculaService.getAll();
        return ResponseEntity.ok().body(peliculas);
    }

    @GetMapping(value = "/{pelicula}",produces = "application/json")
    @Operation(summary = "Retorna una pelicula",description = "Devuelve una pelicula si existe")
    //@LogExecutionTime
    public ResponseEntity<?> getPeliculaByname(@PathVariable("pelicula") String nombrePelicula) {
        Optional<Pelicula> pelicula = peliculaService.findByNombre(nombrePelicula);
        if (pelicula.isPresent()){
            return ResponseEntity.ok().body(pelicula.get());
        }else{
            //return ResponseEntity.status(404).body("No se a encontrado la pelicula solicitada");
            return ResponseEntity.notFound().build();
        }
    }

}
