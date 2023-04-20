package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion,Long> {

    @Query("SELECT f FROM Funcion f WHERE f.peliculaEnFuncion.nombre = ?1")
    public List<Funcion> funcionesPorPelicula(String nombrePelicula);
}
