package com.cineunq.service;

import com.cineunq.dao.ClienteRepository;
import com.cineunq.dao.CompraRepository;
import com.cineunq.dominio.*;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.ICompraService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService {

    private CompraRepository repository;

    private AsientoService asientoService;

    private ClienteService clienteService;

    private FuncionService funcionService;

    @Autowired
    public CompraService(CompraRepository repository, AsientoService asientoService, ClienteService clienteService, FuncionService funcionService) {
        this.repository = repository;
        this.asientoService = asientoService;
        this.clienteService = clienteService;
        this.funcionService = funcionService;
    }

    @Override
    public List<Compra> getAll() {
        return repository.findAll();
    }

    @Override
    public Compra findById(Long id) throws NotFoundException {
        Optional<Compra> compra = repository.findById(id);
        if(compra.isPresent()){
            return compra.get();
        }
        throw new NotFoundException("Compra : No se a encontrado la Compra solicitada");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Compra saveCompra(Long idCliente, Long idFuncion,List<Long> asientos) {
            Cliente cliente = clienteService.findByID(idCliente);
            Funcion funcion = funcionService.findById(idFuncion);
            asientoService.updateAsientos(asientos);
            Compra compra = new Compra(cliente,funcion);
            return repository.save(compra);
    }
}
