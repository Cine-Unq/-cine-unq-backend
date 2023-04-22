package com.cineunq.service;

import com.cineunq.dao.AsientosRepository;
import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IAsientoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Asiento saveAsiento(Asiento p) {
        return repository.save(p);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public List<Asiento> updateAsientos(List<Long> idsAsientosComprados) throws NotFoundException {
        List<Asiento> asientos = new ArrayList<>();
        idsAsientosComprados.forEach(idAsiento -> {
            try {
                asientos.add(this.updateAsiento(idAsiento, EstadoAsiento.OCUPADO));
            } catch (NotFoundException e) {
                throw new MovieUnqLogicException("Asientos : Ocurrio un error al realizar la compra",e);
            }
        });
        return asientos;
    }

    public Asiento updateAsiento(Long id, EstadoAsiento estadoAsiento) throws NotFoundException {
        Asiento asiento = this.findByID(id);
        asiento.setEstaOcupado(estadoAsiento);
        return repository.save(asiento);
    }

    @Override
    public List<Asiento> getAsientosBySala(Long id) {
        return null;
    }

//    @Override
//    public List<Asiento> getAsientosBySala(Long id){
//        return repository.findAsientoBySala(id);
//    }
}
