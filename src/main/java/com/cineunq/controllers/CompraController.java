package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.SaveCompraRequest;
import com.cineunq.controllers.dto.response.AsientoResponse;
import com.cineunq.controllers.dto.response.CompraResponse;
import com.cineunq.dominio.Compra;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.CompraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
@Tag(name = "Compras", description = "Endpoints para las compras")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class CompraController {

    @Autowired
    private CompraService service;

    @GetMapping
    public List<CompraResponse> getAll(){
        return service.getAll().stream().map(CompraResponse::new).toList();
    }

    @GetMapping(path = "/{id}",produces = "application/json")
    public CompraResponse getCompraByID(@PathVariable("id") String id) throws NotFoundException {
        return new CompraResponse(service.findById(Long.parseLong(id)));
    }

    @PostMapping
    public Compra saveCompra(@RequestBody SaveCompraRequest compra){
        return service.saveCompra(compra.getIdCliente(),compra.getIdFuncion(),compra.getIdsAsientos());
    }
}
