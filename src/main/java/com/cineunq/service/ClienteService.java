package com.cineunq.service;

import com.cineunq.dao.ClienteRepository;
import com.cineunq.dominio.Cliente;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente findByID(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new NotFoundException("No se a encontrado el Cliente solicitada ");
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return repository.save(cliente);
    }
}
