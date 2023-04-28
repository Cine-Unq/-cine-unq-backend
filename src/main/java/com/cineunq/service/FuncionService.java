package com.cineunq.service;

import com.cineunq.dao.FuncionRepository;
import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IFuncionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class FuncionService implements IFuncionService {

    private FuncionRepository funcionRepository;

    private SalaService salaService;

    private PeliculaService peliculaService;

    @Autowired
    public FuncionService(FuncionRepository funcionRepository, SalaService salaService, PeliculaService peliculaService) {
        this.funcionRepository = funcionRepository;
        this.salaService = salaService;
        this.peliculaService = peliculaService;
    }

    @Override
    public Funcion saveFuncion(Funcion f) {
        return funcionRepository.save(f);
    }

    public boolean estaSalaOcupada(Long idSala, LocalDateTime fechaInicioFuncion){
        return funcionRepository.estaSalaOcupada(idSala,fechaInicioFuncion) > 0;
    }

    @Transactional(rollbackOn = Exception.class)
    public Funcion saveFuncion(Funcion f,Long idSala) {
        try {
            Sala s1 = salaService.findById(idSala);
            Pelicula p1 = peliculaService.findByID(f.getPeliculaEnFuncion().getId());
            boolean estaOcupada = estaSalaOcupada(idSala,f.getHoraInicio());
            if(!estaOcupada) {
                return funcionRepository.save(f);
            }else {
                throw new MovieUnqLogicException("No se puede crear una Funcion ahora mismo debido a que ya existe una en curso en la sala");
            }

        } catch (NotFoundException e) {
            throw new NotFoundException("Funcion : No se a encontrado la Funcion solicitada");
        }

    }

    public Map<String,List<Funcion>> funcionesPorPelicula(Long idPelicula){
        List<Funcion> funcionesPelicula = funcionRepository.funcionesPorPelicula(idPelicula);
        Map<String,List<Funcion>> funcionesPorSala = new HashMap<>();
        for (Funcion f : funcionesPelicula) {
            if(funcionesPorSala.get(f.getSala().getTipoSala()) != null){
                funcionesPorSala.get(f.getSala().getTipoSala()).add(f);
            }else{
                funcionesPorSala.put(f.getSala().getTipoSala(),List.of(f));
            }
        }
        return funcionesPorSala;
    }

    public Funcion findById(Long id) throws NotFoundException {
        Optional<Funcion> funcion = funcionRepository.findById(id);
        if(funcion.isPresent()){
            return funcion.get();
        }
        throw new NotFoundException("Funcion : No se a encontrado la Funcion solicitada");
    }
}
