package com.cineunq;

import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.builder.PeliculaBuilder;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.PeliculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeliculaServiceTest {

    @Autowired
    private PeliculaService peliculaService;

    private Pelicula p1;

    @BeforeEach
    public void setUp(){
        p1 = peliculaService.savePelicula(new PeliculaBuilder().withNombre("Titanic").withDuracion(120).withImagen("titanicPoster.png").withDescripcion("La de DiCaprio").build());
    }

    @Test
    public void testPeliculaPorIdCuandoSoloExisteUna(){
        Pelicula pTest = peliculaService.findByID(1L);
        assertEquals(p1.getId(),pTest.getId());
    }

    @Test
    public void testDondeNoExistePeliculaPorIdYTiraExcepcion(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Pelicula pTest = peliculaService.findByID(10L);
        });

        String expectedMessage = "No se a encontrado la Pelicula solicitada ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testTodasLasPeliculasDisponibles(){
        List<Pelicula> peliculas = peliculaService.getAll();
        peliculas.forEach(pelicula -> System.out.println(pelicula.getNombre()));
        assertEquals(1,peliculas.size());
    }

    @Test
    public void testPeliculaPorNombre(){
        Pelicula pTest = peliculaService.findByNombre("Titanic");
        assertEquals(p1.getId(),pTest.getId());
    }

    @Test
    public void testDondeNoExistePeliculaConElNombreYTiraExcepcion(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Pelicula pTest = peliculaService.findByNombre("Shrek");
        });

        String expectedMessage = "No se a encontrado la Pelicula solicitada ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
