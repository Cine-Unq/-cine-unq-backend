package com.cineunq.controllers;

import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.SalaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salas")
@Tag(name = "Salas", description = "Endpoints para las salas")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping(value = "/{id}",produces = "application/json")
    private Sala getSalaPorId(Long id) throws NotFoundException {
        return salaService.findById(id);
    }
}
