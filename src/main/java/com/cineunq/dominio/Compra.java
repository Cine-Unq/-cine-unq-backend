package com.cineunq.dominio;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NonNull
    private Cliente clienteCompra;

    @OneToMany
    @NonNull
    private List<Asiento> asientosComprados;

    @OneToOne
    @NonNull
    private Pelicula pelicula; //Posiblemente se valla

    @NonNull
    private LocalDate fechaCompra ;

}
