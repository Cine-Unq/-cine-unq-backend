package com.cineunq.service;

import com.cineunq.dao.ClienteRepository;
import com.cineunq.dominio.Cliente;
import com.cineunq.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return repository.save(cliente);
    }
}
