package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.SalaAsientosRequest;
import com.cineunq.controllers.dto.response.SalasFormatoResponse;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.SalaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/salas/")
@Tag(name = "Salas", description = "Endpoints para las salas")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping(value = "{id}",produces = "application/json")
    private Sala getSalaPorId(@PathVariable("id") String id){
        return salaService.findById(Long.parseLong(id));
    }

    @GetMapping(value = "format") //Map<String,List<Sala>>
    private List<SalasFormatoResponse> salasPorFormatoSala(){ // List<SalasFormatoResponse>
        //.entrySet().stream().map(formato -> new SalasFormatoResponse(formato.getKey(), formato.getValue())).toList();
        Set<Map.Entry<String,List<Sala>>> salas = salaService.salasPorFormatoSala().entrySet();
        return salas.stream().map(e -> new SalasFormatoResponse(e.getKey(),e.getValue())).toList();
    }

    @PostMapping()
    private void crearSala(@RequestBody SalaAsientosRequest sala){
        salaService.testeoSala(sala);
    }


}
