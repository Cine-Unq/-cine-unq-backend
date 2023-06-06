package com.cineunq.service.interfaces;

import com.cineunq.dominio.Pelicula;
import com.cineunq.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPeliculaService {

    List<Pelicula> getAll();

    Pelicula findByID(Long id) throws NotFoundException;

    Pelicula findByNombre(String nombre);

    Pelicula savePelicula(Pelicula p);
}
