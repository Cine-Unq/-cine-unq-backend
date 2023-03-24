package com.cineunq.service;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Pelicula;

import java.util.List;
import java.util.Optional;

public interface IAsientoService {

    List<Asiento> getAll();

    Optional<Asiento> findByID(Long id);

    void saveAsiento(Asiento p);
}
