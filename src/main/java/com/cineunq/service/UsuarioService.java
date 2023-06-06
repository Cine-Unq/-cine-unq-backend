package com.cineunq.service;

import com.cineunq.dao.UsuarioRepository;
import com.cineunq.dominio.Usuario;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IClienteService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario findByID(Long id) {
        Optional<Usuario> cliente = repository.findById(id);
        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new NotFoundException("No se a encontrado el usuario solicitado ");
    }

    @Override
    public Usuario saveCliente(Usuario usuario) {
        return repository.save(usuario);
    }
}
