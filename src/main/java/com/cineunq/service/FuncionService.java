package com.cineunq.service;

import com.cineunq.dao.FuncionRepository;
import com.cineunq.dominio.Funcion;
import com.cineunq.service.interfaces.IFuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
