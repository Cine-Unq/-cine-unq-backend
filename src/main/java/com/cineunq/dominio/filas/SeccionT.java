package com.cineunq.dominio.filas;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class SeccionT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "seccion_id")
    public List<FilaT> filas; //List<SeccionT>

    @Builder
    public SeccionT(List<FilaT> filas) {
        this.filas = filas;
    }
}
