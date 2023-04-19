package com.cineunq.service;

import com.cineunq.dao.ClienteRepository;
import com.cineunq.dao.CompraRepository;
import com.cineunq.dominio.*;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.ICompraService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private ClienteRepository clienteService;

    @Autowired
    private SalaService salaService;

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
        throw new NotFoundException("Compra : No se a encontrado la Compra solicitada");
    }


//    @Override
//    @Transactional(rollbackOn = Exception.class)
//    public Compra saveCompra(Long idCliente, Long idPelicula, List<Long> idsAsientosComprados) {
//        try{
//            Cliente cliente = clienteService.getReferenceById(idCliente);
//            Pelicula pelicula = peliculaService.findByID(idPelicula);
//            List<Asiento> asientos = asientoService.updateAsientos(idsAsientosComprados);
//            Compra compra = new Compra(cliente,asientos,pelicula);
//            return repository.save(compra);
//        }catch (Exception e){
//            throw new MovieUnqLogicException("Compra : Ocurrio un error al realizar la compra",e);
//        }
//    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Compra saveCompra(Long idCliente, Long idSala) {
        try{
            Cliente cliente = clienteService.getReferenceById(idCliente);
            Sala sala = salaService.findById(idSala);
            asientoService.updateAsientos(sala.getAsientosSala().stream().map(Asiento::getId).toList());
            Compra compra = new Compra(cliente,sala);
            return repository.save(compra);
        }catch (Exception e){
            throw new MovieUnqLogicException("Compra : Ocurrio un error al realizar la compra",e);
        }
    }
}
