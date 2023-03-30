package com.cineunq.service;

import com.cineunq.dao.AsientosRepository;
import com.cineunq.dominio.Asiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsientoService implements IAsientoService {

    @Autowired
    private AsientosRepository repository;

    @Override
    public List<Asiento> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Asiento> findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public void saveAsiento(Asiento p) {
        repository.save(p);
    }

    public Asiento updateAsiento(Asiento a){
        return repository.save(a);
    }

    @Override
    public List<Asiento> getAsientosByMovie(Long id){
        return repository.findAsientoByMovie(id);
    }
}
