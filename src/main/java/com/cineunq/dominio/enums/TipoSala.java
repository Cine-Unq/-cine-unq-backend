package com.cineunq.dominio.enums;

public enum TipoSala {
    DOS_D("2D"),TRES_D("3D"),CUATRO_D("4D");

    private String nombre;

    TipoSala(String nombre) {
        this.nombre = nombre;
    }
    public String nombre() {
        return nombre;
    }
}
