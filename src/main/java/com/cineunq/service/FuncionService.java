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

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionService implements IFuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private SalaService salaService;

    @Autowired
    private PeliculaService peliculaService;


    @Override
    public Funcion saveFuncion(Funcion f) {
        return funcionRepository.save(f);
    }

    public boolean estaSalaOcupada(Long idSala, LocalDateTime fechaInicioFuncion){
        return funcionRepository.estaSalaOcupada(idSala,fechaInicioFuncion) > 0;
    }

    @Transactional(rollbackOn = Exception.class)
    public Funcion saveFuncion(Funcion f,Long idSala) throws NotFoundException {
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

    public List<Funcion> funcionesPorPelicula(String nombrePeli){
        return funcionRepository.funcionesPorPelicula(nombrePeli);
    }

    public Funcion findById(Long id) throws NotFoundException {
        Optional<Funcion> funcion = funcionRepository.findById(id);
        if(funcion.isPresent()){
            return funcion.get();
        }
        throw new NotFoundException("Funcion : No se a encontrado la Funcion solicitada");
    }
}
