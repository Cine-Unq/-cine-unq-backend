package com.cineunq.services;

import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.Sala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.SalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalaServiceTest {

    @Autowired
    private SalaService salaService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void testSalaPorIdCuandoSoloExisteUna(){
        Sala salaTest = salaService.findById(1L);
        assertEquals(1L,salaTest.getId());
    }

    @Test
    public void testDondeNoExisteSalaPorIdYTiraExcepcion(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Sala sTest = salaService.findById(10L);
        });

        String expectedMessage = "Compra : No se a encontrado la Sala solicitada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
