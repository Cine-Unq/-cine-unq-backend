package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientosRepository extends JpaRepository<Asiento,Long> {

    @Query("SELECT f.asientosSala FROM Funcion f JOIN f.asientosSala a  WHERE f.id = ?1 ORDER BY a.fila , a.columna" )
    List<Asiento> findAsientoByFuncion(Long idFuncion);


}
