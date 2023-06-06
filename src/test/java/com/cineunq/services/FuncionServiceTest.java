package com.cineunq.services;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Funcion;
import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.FuncionService;
import com.cineunq.service.PeliculaService;
import com.cineunq.service.SalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FuncionServiceTest {

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private PeliculaService peliculaService;

//    @Mock
//    private Pelicula p1;

    @BeforeEach
    public void setUp(){
        //MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCuandoNoExisteFuncionYSeBusca(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Funcion f = funcionService.findById(10L);
        });

        String expectedMessage = "Funcion : No se a encontrado la Funcion solicitada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSaveFuncion() {
//        Mockito.when(p1.getId()).thenReturn(1L);
//        Mockito.when(p1.getDuracion()).thenReturn(100);
        Sala s1 = salaService.findById(1L);
        Pelicula p1 = peliculaService.findByID(1L);
        Funcion f1 = this.funcionService.saveFuncion(Funcion.builder().horaInicio(LocalDateTime.of(2024,1,1,12, 0)).peliculaEnFuncion(p1).sala(s1).asientos(new ArrayList<>()).build());
        assertEquals(2L,f1.getId());
    }

    @Test
    public void testSaveFuncionWithIdSala() {
//        Mockito.when(p1.getId()).thenReturn(1L);
//        Mockito.when(p1.getDuracion()).thenReturn(100);
        Sala s1 = salaService.findById(1L);
        Pelicula p1 = peliculaService.findByID(1L);
        Funcion f1 = this.funcionService.saveFuncion(Funcion.builder().horaInicio(LocalDateTime.of(2024,1,1,12, 0)).peliculaEnFuncion(p1).sala(s1).asientos(new ArrayList<>()).build(),1L);
        assertEquals(2L,f1.getId());
    }

    @Test
    public void testGuardarFuncionCuandoSeLePasaLaSalaEquivocada(){
        Sala s1 = salaService.findById(1L);
        Pelicula p1 = peliculaService.findByID(1L);
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            Funcion f1 = this.funcionService.saveFuncion(Funcion.builder().horaInicio(LocalDateTime.of(2024,1,1,12, 0)).peliculaEnFuncion(p1).sala(s1).asientos(new ArrayList<>()).build(),2L);
        });

        String expectedMessage = "Funcion : No se a podido guardar la funcion";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGuardarFuncionCuandoExiste(){
        Sala s1 = salaService.findById(1L);
        Pelicula p1 = peliculaService.findByID(1L);
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            Funcion f1 = this.funcionService.saveFuncion(Funcion.builder().horaInicio(LocalDateTime.now().plusMinutes(10)).peliculaEnFuncion(p1).sala(s1).asientos(new ArrayList<>()).build(),1L);
        });

        String expectedMessage = "No se puede crear una Funcion ahora mismo debido a que ya existe una en curso en la sala";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCuandoSalaEstaOcupadaRetornaMayor(){
        assertTrue(funcionService.estaSalaOcupada(1L,LocalDateTime.now()));
    }
}
