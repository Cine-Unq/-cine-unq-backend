package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala,Long> {

    @Query("select s from Sala s join InfoTipoSala i where s.tipoSala.id = i.id order by s.nombreSala")
    List<Sala> salasPorInfoTipoSala();

    @Query("select f.sala from Funcion f where f.id = ?1")
    Sala salaPorIdFuncion(Long idFuncion);

}
