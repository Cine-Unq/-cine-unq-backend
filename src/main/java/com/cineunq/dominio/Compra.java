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
    @JoinColumn(name = "clienteCompra_id", referencedColumnName = "id")
    @NonNull
    private Cliente clienteCompra;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private Funcion funcion;

    private LocalDate fechaCompra = LocalDate.now();

    @OneToMany
    @NonNull
    private List<Asiento> asientosComprados;

}
