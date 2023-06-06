package com.cineunq.dao;


import com.cineunq.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findUsuarioByCorreo(String correo);

    Boolean existsByCorreo(String correo);

}
