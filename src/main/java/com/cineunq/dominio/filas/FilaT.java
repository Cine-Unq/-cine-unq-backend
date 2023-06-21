package com.cineunq.dominio.filas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FilaT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Integer cantAsientos;

    public String descripcion; //"A"

    @Builder
    public FilaT(Long id, Integer cantAsientos, String descripcion) {
        this.id = id;
        this.cantAsientos = cantAsientos;
        this.descripcion = descripcion;
    }

    @Builder
    public FilaT(Integer cantAsientos, String descripcion) {
        this.cantAsientos = cantAsientos;
        this.descripcion = descripcion;
    }
}
