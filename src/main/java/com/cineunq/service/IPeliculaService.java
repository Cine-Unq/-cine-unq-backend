package com.cineunq.service;

import com.cineunq.dominio.Pelicula;
import com.cineunq.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPeliculaService {

    List<Pelicula> getAll();

    Pelicula findByID(Long id) throws NotFoundException;

    Optional<Pelicula> findByNombre(String nombre);

    void savePelicula(Pelicula p);
}
