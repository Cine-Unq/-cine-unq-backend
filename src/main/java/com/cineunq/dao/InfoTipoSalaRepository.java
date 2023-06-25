package com.cineunq.dao;

import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.InfoTipoSala;
import com.cineunq.dominio.enums.TipoSala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoTipoSalaRepository extends JpaRepository<InfoTipoSala,Long> {

    InfoTipoSala findByTipoSala(TipoSala tipoSala);

}
