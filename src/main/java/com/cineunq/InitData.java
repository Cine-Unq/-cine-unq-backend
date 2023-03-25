package com.cineunq;

import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.builder.PeliculaBuilder;
import com.cineunq.service.PeliculaService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitData {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PeliculaService peliculaService;
    @PostConstruct
    private void initialize() {
            logger.info("Init Data Using H2 DB");
            fireInitialData();
        }

    private void fireInitialData() {
        Pelicula p = new PeliculaBuilder().withNombre("Avengers 1").withDescripcion("The Avengers").withDuracion(150).withImagen("https://http2.mlstatic.com/D_NQ_NP_888996-MLA32569507268_102019-O.jpg").withAsientos(null).build();
        peliculaService.savePelicula(p);
    }

}
