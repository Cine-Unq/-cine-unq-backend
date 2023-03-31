package com.cineunq.dao;

import com.cineunq.dominio.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {

    Optional<Pelicula> findByNombre(String nombre);

}
