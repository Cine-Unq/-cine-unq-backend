package com.cineunq.service.interfaces;

import com.cineunq.dominio.Cliente;

public interface IClienteService {

    public Cliente getById(Long id);

    public Cliente saveCliente(Cliente cliente);
}
