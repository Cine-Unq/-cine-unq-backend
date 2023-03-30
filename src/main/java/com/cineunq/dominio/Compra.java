package com.cineunq.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cliente clienteCompra;

    @OneToMany
    private List<Asiento> asientosComprados;

    @OneToOne
    private Pelicula pelicula; //Posiblemente se valla

    private LocalDate fechaCompra = LocalDate.now();



}
