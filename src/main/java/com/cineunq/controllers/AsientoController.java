package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.ActualizarAsientoRequest;
import com.cineunq.controllers.dto.response.AsientoResponse;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.service.AsientoService;
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


    @GetMapping("pelicula/funcion/{id}/")
    public ResponseEntity<?> getAsientosByFuncionPelicula(@PathVariable("id") String id) {
        List<AsientoResponse> asientos = service.getAsientosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
        return new ResponseEntity<>(asientos, HttpStatus.OK);
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
