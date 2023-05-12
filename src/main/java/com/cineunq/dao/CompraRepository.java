package com.cineunq.dao;


import com.cineunq.dominio.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

    @Query("SELECT c FROM Compra c where c.usuarioCompra.id = ?1" )
    List<Compra> findCompraByCliente(Long idCliente);

}
