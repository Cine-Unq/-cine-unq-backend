package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion,Long> {

    @Query("SELECT f FROM Funcion f WHERE f.peliculaEnFuncion.id = ?1")
    public List<Funcion> funcionesPorPelicula(Long idPelicula);

    //@Query(value = "select count(*) from funcion f where f.sala_id = :idSala and :fechaInicioFuncion between (f.hora)",nativeQuery = true)
    @Query(value = "select count(f) from Funcion f where f.sala.id = ?1 and ?2 between f.horaInicio and f.horaFin")
    public Integer estaSalaOcupada(Long idSala, LocalDateTime fechaInicioFuncion);

}
