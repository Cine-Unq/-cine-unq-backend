package com.cineunq.dominio;

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
    @JoinColumn(name = "usuarioCompra_id", referencedColumnName = "id_usuario")
    @NonNull
    private Usuario usuarioCompra;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private Funcion funcion;

    private LocalDate fechaCompra = LocalDate.now();

    @OneToMany(fetch = FetchType.EAGER)
    @NonNull
    private List<Asiento> asientosComprados;

}
