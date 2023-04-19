package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombreSala;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JsonIgnore
    public List<Asiento> asientosSala;

    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    public Funcion funcion;

    @Builder
    public Sala(@NonNull String nombreSala, List<Asiento> asientosSala, Funcion funcion) {
        this.nombreSala = nombreSala;
        this.asientosSala = asientosSala;
        this.funcion = funcion;
    }
}
