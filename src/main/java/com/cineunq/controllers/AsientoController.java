package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.ActualizarAsientoRequest;
import com.cineunq.controllers.dto.response.AsientoResponse;
import com.cineunq.controllers.dto.response.AsientosPorFuncionResponse;
import com.cineunq.dominio.Sala;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.service.AsientoService;
import com.cineunq.service.FuncionService;
import com.cineunq.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asientos/")
@Tag(name = "Asientos", description = "Endpoints para los asientos")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class AsientoController {

    @Autowired
    private AsientoService service;

    @Autowired
    private SalaService salaService;


    @GetMapping("pelicula/funcion/{id}/")
    public ResponseEntity<?> getAsientosByFuncionPelicula(@PathVariable("id") String id) {
        Sala salaFuncion = salaService.findSalaByFuncionId(Long.valueOf(id));
        List<AsientoResponse> asientos = service.getAsientosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
        AsientosPorFuncionResponse response = new AsientosPorFuncionResponse(salaFuncion.getColumnas(),salaFuncion.getCantFilas(),asientos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("pelicula/funcion/{id}/admin")
    public ResponseEntity<?> getAsientosByFuncionPeliculaAdmin(@PathVariable("id") String id) {
        List<AsientoResponse> asientos = service.getAsientosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
        return new ResponseEntity<>(asientos, HttpStatus.OK);
    }

    @PostMapping ("pelicula/funcion")
    @Operation(summary = "Endpoint para registrar el escaneo de un codigo QR")
    public ResponseEntity<?> registrarAsientosOcupados(@RequestBody ActualizarAsientoRequest asientos){
        service.registrarAsientosOcupados(asientos.getAsientos(),asientos.getIdCliente(),asientos.getIdCompra());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
