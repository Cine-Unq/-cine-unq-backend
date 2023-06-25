package com.cineunq.dominio;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NonNull
    private String nombreSala;

    private Integer cantFilas;

    private String columnas;

    @ManyToOne
    private InfoTipoSala tipoSala;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sala_id")
    private List<FormaSala> forma;

    @Builder(access = AccessLevel.PUBLIC)
    public Sala(@NonNull String nombreSala, Integer cantFilas, String columnas, InfoTipoSala tipoSala, List<FormaSala> forma) {
        this.nombreSala = nombreSala;
        this.cantFilas = cantFilas;
        this.columnas = columnas;
        this.tipoSala = tipoSala;
        this.forma = forma;
    }
}
