package com.cineunq.dominio;

import com.cineunq.dominio.filas.SeccionT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

//    @NonNull
//    private Integer cantFilas;
//
//    @NonNull
//    private String columnas;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sala_id")
    private List<SeccionT> secciones;

    @ManyToOne
    private InfoTipoSala tipoSala;

//    @Builder(access = AccessLevel.PUBLIC)
//    public Sala(@NonNull String nombreSala, @NonNull Integer cantFilas, @NonNull String columnas, InfoTipoSala tipoSala) {
//        this.nombreSala = nombreSala;
//        this.cantFilas = cantFilas;
//        this.columnas = columnas;
//        this.tipoSala = tipoSala;
//    }

    @Builder(access = AccessLevel.PUBLIC)
    public Sala(@NonNull String nombreSala, List<SeccionT> secciones, InfoTipoSala tipoSala) {
        this.nombreSala = nombreSala;
        this.secciones = secciones;
        this.tipoSala = tipoSala;
    }
}
