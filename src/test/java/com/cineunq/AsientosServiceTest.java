package com.cineunq;

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
    private FuncionService funcionService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuarioService;

    @Spy
    private Compra c1;

    private Asiento a1;

    private Asiento a2;

    @BeforeEach
    public void setUp(){
        //compraService = mock(CompraService.class);
        MockitoAnnotations.openMocks(this);
        a1 = asientoService.saveAsiento(Asiento.builder().columna("A").fila("1").estado(EstadoAsiento.LIBRE).build());
    }

    @Test
    public void testCantAsientosCuandoSoloExisteUnAsiento(){
        List<Asiento> asientos = asientoService.getAll();
        assertEquals(1,asientos.size());
    }

    @Test
    public void testIdAsientoCuandoSoloExisteUnAsiento(){
        Asiento asiento = asientoService.findByID(1L);
        assertEquals(asiento.getId(),a1.getId());
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
        asientoService.updateAsientos(List.of(1L),EstadoAsiento.RESERVADO);
        Asiento asiento = asientoService.findByID(1L);
        assertEquals(1L,asiento.getId());
        assertEquals(EstadoAsiento.RESERVADO,asiento.getEstado());
    }

    @Test
    public void testAsientoCambiaDeEstadoDeReservadoAOcupado(){
        a2 = asientoService.saveAsiento(Asiento.builder().columna("A").fila("2").estado(EstadoAsiento.RESERVADO).build());
        asientoService.updateAsientos(List.of(2L),EstadoAsiento.OCUPADO);
        Asiento asiento = asientoService.findByID(2L);
        assertEquals(2L,asiento.getId());
        assertEquals(EstadoAsiento.OCUPADO,asiento.getEstado());
    }

    @Test
    public void testAsientoCambiaDeEstadoDeLibreAOcupadoPeroTiraError(){
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            asientoService.updateAsientos(List.of(1L),EstadoAsiento.OCUPADO);
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
        a2 = Asiento.builder().columna("A").fila("2").estado(EstadoAsiento.LIBRE).build();
        Sala sala = Sala.builder().tipoSala("2d").nombreSala("sala 1").cantFilas(1).columnas("A").build();
        salaService.saveSala(sala);
        Pelicula p1 = peliculaService.savePelicula(Pelicula.builder().nombre("Titanic").duracion(120).imagen("titanicPoster.png").descripcion("La de DiCaprio").build());
        //Mockito.when(p1.getDuracion()).thenReturn(100);
        Funcion f1 = Funcion.builder().horaInicio(LocalDateTime.now()).peliculaEnFuncion(p1).sala(sala).asientos(List.of(a2)).build();
        funcionService.saveFuncion(f1);
        List<Asiento> asientosFuncion = asientoService.getAsientosPorFuncion(1L);

        assertEquals(1,asientosFuncion.size());
    }

    @Test
    public void testCorrespondenAsientosConCompra(){
        assertTrue(asientoService.correspondenAsientosConCompra(List.of(a1),List.of(1L)));
    }

    @Test
    public void testregistrarAsientosOcupados(){
        Long idCliente = 1L;
        Long idCompra = 1L;
        a2 = Asiento.builder().build();
        Pelicula p1 = peliculaService.savePelicula(Pelicula.builder().duracion(100).build());
        Sala s1 = salaService.saveSala(Sala.builder().build());
        usuarioService.saveCliente(Usuario.builder().build());
        Funcion f1 = Funcion.builder().horaInicio(LocalDateTime.now()).peliculaEnFuncion(p1).sala(s1).asientos(List.of(a2)).build();
        funcionService.saveFuncion(f1);
        List<Long> asientos = List.of(a2.getId());
        compraService.saveCompra(1L,1L,asientos);
        asientoService.registrarAsientosOcupados(asientos,idCliente,idCompra);
        Asiento asientoPostCambio =  asientoService.findByID(2L);
        assertEquals(EstadoAsiento.OCUPADO,asientoPostCambio.getEstado());
    }

    @Test
    public void testregistrarAsientosOcupadosFallaPorQueLaCompraNoEsDelCliente(){
        Exception exception = assertThrows(MovieUnqLogicException.class, () -> {
            Long idCliente = 2L;
            Long idCompra = 1L;
            a2 = Asiento.builder().build();
            Pelicula p1 = peliculaService.savePelicula(Pelicula.builder().duracion(100).build());
            Sala s1 = salaService.saveSala(Sala.builder().build());
            usuarioService.saveCliente(Usuario.builder().build());
            Funcion f1 = Funcion.builder().horaInicio(LocalDateTime.now()).peliculaEnFuncion(p1).sala(s1).asientos(List.of(a2)).build();
            funcionService.saveFuncion(f1);
            List<Long> asientos = List.of(a2.getId());
            compraService.saveCompra(1L,1L,asientos);
            asientoService.registrarAsientosOcupados(asientos,idCliente,idCompra);
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
            Long idCliente = 1L;
            Long idCompra = 1L;
            a2 = Asiento.builder().build();
            Pelicula p1 = peliculaService.savePelicula(Pelicula.builder().duracion(100).build());
            Sala s1 = salaService.saveSala(Sala.builder().build());
            usuarioService.saveCliente(Usuario.builder().build());
            Funcion f1 = Funcion.builder().horaInicio(LocalDateTime.now()).peliculaEnFuncion(p1).sala(s1).asientos(List.of(a2)).build();
            funcionService.saveFuncion(f1);
            List<Long> asientos = List.of(a1.getId());
            compraService.saveCompra(1L,1L,asientos);
            asientoService.registrarAsientosOcupados(List.of(2L),idCliente,idCompra);
            Asiento asientoPostCambio =  asientoService.findByID(2L);
            assertEquals(EstadoAsiento.OCUPADO,asientoPostCambio.getEstado());
        });

        String expectedMessage = "Los asientos no corresponden con los de la compra";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

}
