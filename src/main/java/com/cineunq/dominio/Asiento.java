package com.cineunq.dominio;

import com.cineunq.dominio.enums.EstadoAsiento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private EstadoAsiento estaOcupado;

    @NonNull
    private String columna;

    @NonNull
    private String fila;

}
