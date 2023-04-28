package com.cineunq.service;

import com.cineunq.dao.SalaRepository;
import com.cineunq.dominio.Compra;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.ICompraService;
import com.cineunq.service.interfaces.ISalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaService implements ISalaService {

    @Autowired
    private SalaRepository repository;


    @Override
    public Sala saveSala(Sala s) {
        return repository.save(s);
    }

    @Override
    public Sala findById(Long idSala) throws NotFoundException {
        Optional<Sala> sala = repository.findById(idSala);
        if(sala.isPresent()){
            return sala.get();
        }
        throw new NotFoundException("Compra : No se a encontrado la Sala solicitada");
    }
}
