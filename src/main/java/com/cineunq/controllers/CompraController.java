package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.SaveCompraRequest;
import com.cineunq.dominio.Compra;
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
    public List<Compra> getAll(){
        return service.getAll();
    }

    @PostMapping
    public Compra saveCompra(@RequestBody SaveCompraRequest compra){
        Compra c = new Compra(compra.getCliente(),compra.getAsientosComprados(),compra.getPelicula());
        return service.saveCompra(c);
    }
}
