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
        Pelicula p = new PeliculaBuilder().withNombre("Avengers").withDescripcion("The Avengers").withDuracion(150).withImagen("Error").withAsientos(null).build();
        peliculaService.savePelicula(p);
    }

}
