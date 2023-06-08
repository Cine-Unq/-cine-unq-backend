package com.cineunq.dominio;

import com.cineunq.dominio.enums.TipoSala;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class InfoTipoSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Double precio;

    @Enumerated(EnumType.STRING)
    public TipoSala tipoSala;

    @Builder(access = AccessLevel.PUBLIC)
    public InfoTipoSala(Double precio, TipoSala tipoSala) {
        this.precio = precio;
        this.tipoSala = tipoSala;
    }
}
