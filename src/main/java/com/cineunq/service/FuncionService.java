package com.cineunq.service;

import com.cineunq.dao.FuncionRepository;
import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IFuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionService implements IFuncionService {

    @Autowired
    private FuncionRepository funcionRepository;


    @Override
    public Funcion saveFuncion(Funcion f) {
        return funcionRepository.save(f);
    }

    public List<Funcion> funcionesPorPelicula(String nombrePeli){
        return funcionRepository.funcionesPorPelicula(nombrePeli);
    }

    public Funcion findById(Long id) throws NotFoundException {
        Optional<Funcion> funcion = funcionRepository.findById(id);
        if(funcion.isPresent()){
            return funcion.get();
        }
        throw new NotFoundException("Compra : No se a encontrado la Funcion solicitada");
    }
}
