package com.cineunq.service;

import com.cineunq.dominio.Pelicula;

import java.util.List;
import java.util.Optional;

public interface IPeliculaService {

    List<Pelicula> getAll();

    Optional<Pelicula> findByID(Long id);

    Optional<Pelicula> findByNombre(String nombre);

    void savePelicula(Pelicula p);
}
