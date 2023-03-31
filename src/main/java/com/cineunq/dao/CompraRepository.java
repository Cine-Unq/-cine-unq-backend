package com.cineunq.dao;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

}
