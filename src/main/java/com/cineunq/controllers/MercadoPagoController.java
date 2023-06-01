package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.SaveCompraRequest;
import com.cineunq.dominio.Compra;
import com.cineunq.service.CompraService;
import com.cineunq.service.MPService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mp")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class MercadoPagoController {

    @Autowired
    private MPService service;

    @Autowired
    private CompraService compraService;

    @PostMapping("/createAndRedirect")
    public ResponseEntity<?> createAndRecirect(@RequestBody SaveCompraRequest compraDto) throws MPException, MPApiException {
        Compra compra = compraService.saveCompra(compraDto.getIdCliente(),compraDto.getIdFuncion(),compraDto.getIdsAsientos());
        String response = service.generarCompra(compraDto.getIdsAsientos().size(),compra.getId());
        Map<String,String> responseFinal = new HashMap<>();
        responseFinal.put("link",response);
        return new ResponseEntity<>(responseFinal, HttpStatus.OK);
    }
}
