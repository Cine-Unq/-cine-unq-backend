package com.cineunq.services;

import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.FuncionService;
import com.cineunq.service.PeliculaService;
import com.cineunq.service.SalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeliculaServiceTest {

    @Autowired
    private PeliculaService peliculaService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testCuandoExisteUnaPelicula(){
        Pelicula pTest = peliculaService.findByID(1L);
        assertEquals(1L,pTest.getId());
        assertEquals("The Avengers",pTest.getNombre());
        assertEquals("Avengers",pTest.getDescripcion());
        assertEquals(150,pTest.getDuracion());
        assertEquals("avengers.png",pTest.getImagen());
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
        assertEquals(1,peliculas.size());
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

    @Test
    public void testDePeliculaConFuncion(){
        List<Pelicula> peliculas = peliculaService.peliculasConFunciones();
        assertEquals(1,peliculas.size());
    }
}
