package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.ActualizarAsientoRequest;
import com.cineunq.controllers.dto.response.AsientoResponse;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.service.AsientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AsientoResponse> getAsientosByFuncionPelicula(@PathVariable("id") String id) {
        List<AsientoResponse> asientos = service.getAsientosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
        return asientos;
    }

    @GetMapping("pelicula/funcion/{id}/admin")
    public List<AsientoResponse> getAsientosByFuncionPeliculaAdmin(@PathVariable("id") String id) {
        List<AsientoResponse> asientos = service.getAsientosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
        return asientos;
    }

//    @GetMapping("pelicula/funcion/{id}")
//    public List<AsientoResponse> getAsientosOcupadosByFuncionPelicula(@PathVariable("id") String id,@RequestParam(name = "state") String ocupadoOReservado) {
//        List<AsientoResponse> asientos ;
//        if(ocupadoOReservado == "ocupado"){
//            asientos = service.getAsientosOcupadosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
//        }else{
//            asientos = service.getAsientosReservadosPorFuncion(Long.parseLong(id)).stream().map(AsientoResponse::new).toList();
//        }
//        return asientos;
//    }

    @PostMapping ("pelicula/funcion")
    @Operation(summary = "Endpoint para registrar el escaneo de un codigo QR")
    public void registrarAsientosOcupados(@RequestBody ActualizarAsientoRequest asientos) {
        service.registrarAsientosOcupados(asientos.getAsientos(),asientos.getIdCliente(),asientos.getIdCompra());
    }
}
