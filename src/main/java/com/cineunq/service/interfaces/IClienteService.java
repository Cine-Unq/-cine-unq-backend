package com.cineunq.service.interfaces;

import com.cineunq.dominio.Cliente;

public interface IClienteService {

    Cliente getById(Long id);

    Cliente saveCliente(Cliente cliente);
}
