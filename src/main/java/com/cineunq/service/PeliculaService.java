package com.cineunq.service;


import com.cineunq.dao.PeliculaRepository;
import com.cineunq.dominio.Pelicula;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService implements IPeliculaService {

    @Autowired
    private PeliculaRepository repository;

    public void savePelicula(Pelicula p){
        repository.save(p);
    }

    @Override
    public List<Pelicula> getAll() {
        return repository.findAll();
    }

    @Override
    public Pelicula findByID(Long id) throws NotFoundException {
        Optional<Pelicula> pelicula = repository.findById(id);
        if(pelicula.isPresent()) {
            return pelicula.get();
        }
        throw new NotFoundException("No se a encontrado la Pelicula solicitada ");
    }

    @Override
    public Optional<Pelicula> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}
