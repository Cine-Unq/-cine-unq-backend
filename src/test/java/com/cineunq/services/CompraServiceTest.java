package com.cineunq.services;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Compra;
import com.cineunq.dominio.Pelicula;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.CompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CompraServiceTest {

    @Autowired
    private CompraService compraService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void comprasCuandoExisteCliente(){
        List<Compra> listCompra = compraService.getComprasPorCliente(1L);
        Compra c1 = listCompra.get(0);
        List<Long> asientosLong = c1.getAsientosComprados().stream().map(Asiento::getId).toList();
        assertEquals(1,listCompra.size());
        assertEquals(1L,c1.getId());
        assertEquals(1L,c1.getUsuarioCompra().getId());
        assertEquals(1L,c1.getFuncion().getId());
        assertEquals(List.of(1L),asientosLong);
    }

    @Test
    public void todasLasCompras(){
        List<Compra> listCompra = compraService.getAll();
        assertEquals(1,listCompra.size());
    }

    @Test
    public void compraPorId(){
        Compra c1 = compraService.findById(1L);
        List<Long> asientosLong = c1.getAsientosComprados().stream().map(Asiento::getId).toList();
        assertEquals(1L,c1.getId());
        assertEquals(1L,c1.getUsuarioCompra().getId());
        assertEquals(1L,c1.getFuncion().getId());
        assertEquals(List.of(1L),asientosLong);
    }

    @Test
    public void testDondeNoExisteCompraPorIdYTiraExcepcion(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Compra c1 = compraService.findById(10L);
        });

        String expectedMessage = "Compra : No se a encontrado la Compra solicitada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testRealizarCompra(){
        Compra c2 = compraService.saveCompra(1L,1L,List.of(2L));
        List<Long> asientosLong = c2.getAsientosComprados().stream().map(Asiento::getId).toList();
        assertEquals(2L,c2.getId());
        assertEquals(1L,c2.getUsuarioCompra().getId());
        assertEquals(1L,c2.getFuncion().getId());
        assertEquals(List.of(2L),asientosLong);
    }
}
