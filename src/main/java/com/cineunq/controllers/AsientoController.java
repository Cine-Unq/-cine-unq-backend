package com.cineunq.controllers;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Pelicula;
import com.cineunq.service.AsientoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asientos")
@Tag(name = "Asientos", description = "Endpoints para los asientos")
public class AsientoController {

    @Autowired
    private AsientoService service;

    @PutMapping
    public Asiento actualizarAsiento(@RequestBody Asiento a){
        return service.updateAsiento(a);
    }

    @GetMapping("/pelicula/{id}")
    public List<Asiento> getAsientosByPelicula(@PathVariable("id") String id) {
        List<Asiento> asientos = service.getAsientosByMovie(Long.parseLong(id));
        return asientos;
    }
}
