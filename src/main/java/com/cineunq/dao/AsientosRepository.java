package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsientosRepository extends JpaRepository<Asiento,Long> {


}
