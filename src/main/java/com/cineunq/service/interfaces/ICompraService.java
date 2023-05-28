package com.cineunq.service.interfaces;

import com.cineunq.dominio.Compra;
import com.cineunq.exceptions.NotFoundException;

import java.util.List;

public interface ICompraService {

    List<Compra> getAll();

    Compra findById(Long id) throws NotFoundException;

    Compra saveCompra(Long idCliente, Long idSala,List<Long> asientos) throws NotFoundException;
}
