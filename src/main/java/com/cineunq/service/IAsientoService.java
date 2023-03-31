package com.cineunq.service;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAsientoService {

    List<Asiento> getAll();

    Asiento findByID(Long id) throws NotFoundException;

    void saveAsiento(Asiento p);

    Asiento updateAsiento(Long id, EstadoAsiento estadoAsiento) throws NotFoundException;

    List<Asiento> getAsientosByMovie(Long id);
}
