package com.cineunq.service;

import com.cineunq.dao.CompraRepository;
import com.cineunq.dominio.*;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.ICompraService;
import jakarta.transaction.Transactional;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FuncionService funcionService;


//    @Autowired
//    public CompraService(CompraRepository repository, AsientoService asientoService, UsuarioService usuarioService, FuncionService funcionService) {
//        this.repository = repository;
//        this.asientoService = asientoService;
//        this.usuarioService = usuarioService;
//        this.funcionService = funcionService;
//    }

    public List<Compra> getComprasPorCliente(Long idCliente){
        return repository.findCompraByCliente(idCliente);
    }

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

//    public Compra getCompraQR(Long id) throws NotFoundException {
//        Optional<Compra> compra = repository.findById(id);
//        if(compra.isPresent()){
//            return compra.get();
//        }
//        throw new NotFoundException("Compra : No se a encontrado la Compra solicitada");
//    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Compra saveCompra(Long idCliente, Long idFuncion,List<Long> asientos) {
            Usuario usuario = usuarioService.findByID(idCliente);
            Funcion funcion = funcionService.findById(idFuncion);
            List<Asiento> asientosComprados = asientoService.updateAsientos(asientos, EstadoAsiento.RESERVADO);
            Compra compra = new Compra(usuario,funcion,asientosComprados);
            return repository.save(compra);
    }
}
