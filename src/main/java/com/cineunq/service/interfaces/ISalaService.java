package com.cineunq.service.interfaces;

import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;

public interface ISalaService {

    Sala saveSala(Sala s);

    Sala findById(Long idSala) throws NotFoundException;
}
