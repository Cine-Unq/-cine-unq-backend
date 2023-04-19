package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private Cliente clienteCompra;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @NonNull
    private List<Asiento> asientosComprados;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private Pelicula pelicula; //Posiblemente se valla

    @NonNull
    private LocalDate fechaCompra = LocalDate.now();

}
