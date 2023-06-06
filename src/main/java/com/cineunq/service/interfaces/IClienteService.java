package com.cineunq.service.interfaces;

import com.cineunq.dominio.Usuario;

public interface IClienteService {

    Usuario findByID(Long id);

    Usuario saveCliente(Usuario usuario);
}
