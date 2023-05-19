package com.cineunq;

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

//    @Spy
//    Sala sala;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private SalaService salaService;

    private Pelicula p1;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        p1 = peliculaService.savePelicula(Pelicula.builder().nombre("Titanic").duracion(120).imagen("titanicPoster.png").descripcion("La de DiCaprio").build());
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

    @Test
    public void testDePeliculaConFuncion(){
        Sala sala = Sala.builder().tipoSala("2d").nombreSala("sala 1").cantFilas(1).columnas("AB").build();
        salaService.saveSala(sala);
        Funcion f1 = Funcion.builder().horaInicio(LocalDateTime.now()).peliculaEnFuncion(p1).sala(sala).asientos(new ArrayList<>()).build();
        funcionService.saveFuncion(f1);
        List<Pelicula> peliculas = peliculaService.peliculasConFunciones();
        assertEquals(1,peliculas.size());
    }
}
