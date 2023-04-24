package com.cineunq.service.interfaces;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAsientoService {

    List<Asiento> getAll();

    Asiento findByID(Long id) throws NotFoundException;

    Asiento saveAsiento(Asiento p);

    List<Asiento> updateAsientos(List<Long> ids) throws NotFoundException;

    Asiento updateAsiento(Long id) throws NotFoundException;

    List<Asiento> getAsientosBySala(Long id);
}
