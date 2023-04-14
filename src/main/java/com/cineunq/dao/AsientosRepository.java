package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientosRepository extends JpaRepository<Asiento,Long> {

    @Query("SELECT p.asientos FROM Pelicula p JOIN p.asientos a WHERE p.id = ?1 ORDER BY a.fila, a.columna" )
    List<Asiento> findAsientoByMovie(Long idMovie);

}
