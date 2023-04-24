package com.cineunq.service.interfaces;

import com.cineunq.dominio.Cliente;

public interface IClienteService {

    Cliente findByID(Long id);

    Cliente saveCliente(Cliente cliente);
}
