package com.cineunq.dominio;

import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AsientoTest {

    @Test
    public void cambiaDeEstadoAsientoOcupadoAOcupado(){
        Asiento a1 = Asiento.builder().estado(EstadoAsiento.OCUPADO).columna("A").fila("1").build();
        Exception exception = assertThrows(MovieUnqLogicException.class, a1::ocuparAsiento);

        String expectedMessage = "El asiento ya fue ocupado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void cambiaDeEstadoAsientoOcupadoAReservado(){
        Asiento a1 = Asiento.builder().estado(EstadoAsiento.OCUPADO).columna("A").fila("1").build();
        Exception exception = assertThrows(MovieUnqLogicException.class, a1::reservarAsiento);

        String expectedMessage = "No se puede ocupar un asiento que ya fue ocupado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void cambiaDeEstadoAsientoReservadoAReservado(){
        Asiento a1 = Asiento.builder().estado(EstadoAsiento.RESERVADO).columna("A").fila("1").build();
        Exception exception = assertThrows(MovieUnqLogicException.class, a1::reservarAsiento);

        String expectedMessage = "El asiento ya fue reservado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
