package com.cineunq;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.builder.AsientoBuilder;
import com.cineunq.dominio.builder.PeliculaBuilder;
import com.cineunq.service.AsientoService;
import com.cineunq.service.PeliculaService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private AsientoService asientoService;

    @PostConstruct
    private void initialize() {
            logger.info("Init Data Using H2 DB");
            fireInitialData();
        }

    private void fireInitialData() {
        Asiento a1 = new AsientoBuilder().withNrAsiento("A1").withEstaOcupado(false).build();
        Asiento a2 = new AsientoBuilder().withNrAsiento("A2").withEstaOcupado(true).build();
        asientoService.saveAsiento(a1);
        asientoService.saveAsiento(a2);
        Pelicula p = new PeliculaBuilder().withNombre("Avengers 1").withDescripcion("The Avengers").withDuracion(150).withImagen("https://http2.mlstatic.com/D_NQ_NP_888996-MLA32569507268_102019-O.jpg").withAsientos(List.of(a1,a2)).build();
        Pelicula p2 = new PeliculaBuilder().withNombre("John Wick").withDescripcion("La ciudad de Nueva York se llena de balas cuando John Wick, un exasesino a sueldo, regresa de su retiro para enfrentar a los mafiosos rusos, liderados por Viggo Tarasov, que destruyeron todo aquello que él amaba y pusieron precio a su cabeza").withDuracion(114).withImagen("https://http2.mlstatic.com/D_NQ_NP_637824-MLA40163107899_122019-O.jpg").withAsientos(null).build();
        peliculaService.savePelicula(p);
        peliculaService.savePelicula(p2);
    }

}
