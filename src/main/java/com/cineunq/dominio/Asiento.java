package com.cineunq.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean estaOcupado;

    private String nrAsiento;


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }


    public Asiento() {
    }

    public Asiento(boolean estaOcupado, String nrAsiento) {
        this.estaOcupado = estaOcupado;
        this.nrAsiento = nrAsiento;
    }
}
