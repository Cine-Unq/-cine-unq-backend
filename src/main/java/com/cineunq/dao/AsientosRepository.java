package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientosRepository extends JpaRepository<Asiento,Long> {

    @Query("SELECT s.asientosSala FROM Sala s JOIN s.asientosSala a WHERE s.id = ?1 ORDER BY a.fila, a.columna" )
    List<Asiento> findAsientoBySala(Long idSala);

}
