package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NonNull
    public String nombreSala;

    @NonNull
    public Integer cantFilas;

    @NonNull
    private String columnas ;

    @NonNull
    private String tipoSala ;

    @Builder(access = AccessLevel.PUBLIC)
    public Sala( String nombreSala,  Integer cantFilas,  String columnas,  String tipoSala) {
        this.nombreSala = nombreSala;
        this.cantFilas = cantFilas;
        this.columnas = columnas;
        this.tipoSala = tipoSala;
    }
}
