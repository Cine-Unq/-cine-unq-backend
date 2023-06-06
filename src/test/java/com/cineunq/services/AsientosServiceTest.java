package com.cineunq.services;

import com.cineunq.dominio.*;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.exceptions.MovieUnqLogicException;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AsientosServiceTest {

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private CompraService compraService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void testCantAsientosCuandoSoloExisteUnAsiento(){
        List<Asiento> asientos = asientoService.getAll();
        assertEquals(2,asientos.size());
    }

    @Test
    public void testIdAsientoCuandoSoloExisteUnAsiento(){
        Asiento asiento = asientoService.findByID(2L);
        assertEquals(2L,asiento.getId());
        assertEquals("A",asiento.getColumna());
        assertEquals("2",asiento.getFila());
        assertEquals(EstadoAsiento.LIBRE,asiento.getEstado());
    }

    @Test
    public void testIdAsientoIncorrectoCuandoSoloExisteUnAsiento(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Asiento aTest = asientoService.findByID(10L);
        });

        String expectedMessage = "No se a encontrado el Asiento solicitada ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAsientoCambiaDeEstadoDeLibreAReservado(){
        asientoService.updateAsientos(List.of(2L),EstadoAsiento.RESERVADO);
        Asiento asiento = asientoService.findByID(2L);
        assertEquals(2L,asiento.getId());
        assertEquals(EstadoAsiento.RESERVADO,asiento.getEstado());
    }

    @Test
    public void testAsientoCambiaDeEstadoDeReservadoAOcupado(){
        //a2 = asientoService.saveAsiento(Asiento.builder().columna("A").fila("2").estado(EstadoAsiento.RESERVADO).build());
        asientoService.updateAsientos(List.of(1L),EstadoAsiento.OCUPADO);
        Asiento asiento = asientoService.findByID(1L);
        assertEquals(1L,asiento.getId());
        assertEquals(EstadoAsiento.OCUPADO,asiento.getEstado());
    }

    @Test
    public void testAsientoCambiaDeEstadoDeLibreAOcupadoPeroTiraError(){
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            asientoService.updateAsientos(List.of(2L),EstadoAsiento.OCUPADO);
        });

        String expectedMessage = "No se puede ocupar un asiento que no fue reservado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAsientoQueNoExisteCambiaDeEstadoDeLibreAReservadoPeroTiraError(){
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            asientoService.updateAsientos(List.of(10L),EstadoAsiento.RESERVADO);
        });

        String expectedMessage = "Asientos : Ocurrio un error al realizar la compra";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testAsientosPorFuncion(){
        List<Asiento> asientosFuncion = asientoService.getAsientosPorFuncion(1L);
        assertEquals(2,asientosFuncion.size());
    }

    @Test
    public void testCorrespondenAsientosConCompra(){
        Compra c1 = compraService.findById(1L);
        assertTrue(asientoService.correspondenAsientosConCompra(c1.getAsientosComprados(),List.of(1L)));
    }

    @Test
    public void testregistrarAsientosOcupados(){
        compraService.saveCompra(1L,1L,List.of(2L));
        asientoService.registrarAsientosOcupados(List.of(2L),1L,2L);
        Asiento asientoPostCambio =  asientoService.findByID(2L);
        assertEquals(EstadoAsiento.OCUPADO,asientoPostCambio.getEstado());
    }

    @Test
    public void testregistrarAsientosOcupadosFallaPorQueLaCompraNoEsDelCliente(){
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            asientoService.registrarAsientosOcupados(List.of(2L),2L,1L);
            Asiento asientoPostCambio =  asientoService.findByID(2L);
            assertEquals(EstadoAsiento.OCUPADO,asientoPostCambio.getEstado());
        });

        String expectedMessage = "La compra no corresponde con el cliente";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    public void testregistrarAsientosOcupadosFallaPorQueLosAsientosNoCoincidenConCompra(){
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            asientoService.registrarAsientosOcupados(List.of(2L),1L,1L);
            Asiento asientoPostCambio =  asientoService.findByID(2L);
            assertEquals(EstadoAsiento.OCUPADO,asientoPostCambio.getEstado());
        });

        String expectedMessage = "Los asientos no corresponden con los de la compra";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
