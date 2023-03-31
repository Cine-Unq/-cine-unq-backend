package com.cineunq.service;

import com.cineunq.dao.AsientosRepository;
import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.NotFoundException;
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
    public Asiento findByID(Long id) throws NotFoundException {
        Optional<Asiento> asiento = repository.findById(id);
        if(asiento.isPresent()){
            return asiento.get();
        }
        throw new NotFoundException("No se a encontrado el Asiento solicitada ");
    }

    @Override
    public void saveAsiento(Asiento p) {
        repository.save(p);
    }

    public Asiento updateAsiento(Long id, EstadoAsiento estadoAsiento) throws NotFoundException {
        Asiento asiento = this.findByID(id);
        asiento.setEstaOcupado(estadoAsiento);
        return repository.save(asiento);
    }

    @Override
    public List<Asiento> getAsientosByMovie(Long id){
        return repository.findAsientoByMovie(id);
    }
}
