package com.cineunq.service;

import com.cineunq.dao.ClienteRepository;
import com.cineunq.dao.CompraRepository;
import com.cineunq.dominio.Compra;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private AsientoService asientoService;

    @Override
    public List<Compra> getAll() {
        return repository.findAll();
    }

    @Override
    public Compra findById(Long id) throws NotFoundException {
        Optional<Compra> compra = repository.findById(id);
        if(compra.isPresent()){
            return compra.get();
        }
        throw new NotFoundException("No se a encontrado la Compra solicitada");
    }

    @Override
    public Compra saveCompra(Compra compra) {
        compra.getAsientosComprados().forEach(asiento -> {
            try {
                asientoService.updateAsiento(asiento.getId(), EstadoAsiento.OCUPADO);
            } catch (NotFoundException e) {
                throw new MovieUnqLogicException("Compra : Ocurrio un error al realizar la compra",e);
            }
        });
        return repository.save(compra);
    }
}
