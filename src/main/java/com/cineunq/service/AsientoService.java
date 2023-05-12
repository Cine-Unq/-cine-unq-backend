package com.cineunq.service;

import com.cineunq.dao.AsientosRepository;
import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Compra;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IAsientoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AsientoService implements IAsientoService {

    @Autowired
    private AsientosRepository repository;

    @Autowired
    private CompraService compraService;

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
    public List<Asiento> updateAsientos(List<Long> idsAsientosComprados,EstadoAsiento estado) throws NotFoundException {
        List<Asiento> asientos = new ArrayList<>();
        idsAsientosComprados.forEach(idAsiento -> {
            try {
                asientos.add(this.updateAsiento(idAsiento,estado));
            } catch (NotFoundException e) {
                throw new MovieUnqLogicException("Asientos : Ocurrio un error al realizar la compra",e);
            }
        });
        return asientos;
    }

    public Asiento updateAsiento(Long id,EstadoAsiento estado) throws NotFoundException {
        Asiento asiento = this.findByID(id);
        if (estado == EstadoAsiento.OCUPADO) {
            asiento.ocuparAsiento();
        } else {
            asiento.reservarAsiento();
        }
        return repository.save(asiento);
    }

    @Override
    public List<Asiento> getAsientosPorFuncion(Long id) {
        return repository.findAsientoByFuncion(id);
    }


    public void registrarAsientosOcupados(List<Long> asientos, Long idCliente , Long idCompra) {
        Compra compra = compraService.findById(idCompra);
        if(!Objects.equals(compra.getUsuarioCompra().getId(), idCliente)){
            throw new MovieUnqLogicException("La compra no corresponde con el cliente");
        }
        if(!correspondenAsientosConCompra(compra.getAsientosComprados(),asientos)){
            throw new MovieUnqLogicException("Los asientos no corresponden con los de la compra");
        }
        updateAsientos(asientos,EstadoAsiento.OCUPADO);
    }

    public boolean correspondenAsientosConCompra(List<Asiento> asientosCompra, List<Long> asientosIds){
        List<Long> asientosCompraSorted = asientosCompra.stream().map(Asiento::getId).toList();
        return new HashSet<>(asientosCompraSorted).containsAll(asientosIds);
    }
}
