package com.cineunq.controller;

import com.cineunq.security.JwtGenerador;
import com.cineunq.service.AsientoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private JwtGenerador jwtProvider;

    private String token;

    @BeforeEach
    public void setUp() {
        this.token = this.jwtProvider.generarTokenByUsername("admin");
    }

    @Test
    public void testCuandoSoloExisteUnaCompraTraigoTodasLasCompras() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compra/").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sala", Matchers.is("S1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].asientosOcupados.size()", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].asientosReservados.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].compraID", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cliente", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clienteID", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pelicula", Matchers.is("The Avengers")));
    }

    @Test
    public void testCuandoExisteUnaCompraTraigoEsaCompra() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compra/1").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sala", Matchers.is("S1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.asientosOcupados.size()", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.asientosReservados.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compraID", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clienteID", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pelicula", Matchers.is("The Avengers")));
    }

    @Test
    public void testCuandoExisteUnaCompraTraigoEsaCompraCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compra/cliente/1").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sala", Matchers.is("S1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].asientosOcupados.size()", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].asientosReservados.size()", Matchers.is(1)));
    }

    @Test
    public void testCuandoExisteUnaCompraTraigoEsaCompraYOcupoAsiento() throws Exception {
        asientoService.registrarAsientosOcupados(List.of(1L),1L,1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/compra/1").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sala", Matchers.is("S1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.asientosOcupados.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.asientosReservados.size()", Matchers.is(0)));
    }

    @Test
    public void testCuandoSeGuardaUnaCompra() throws Exception {
        mockMvc.perform(post("/compra/").header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idCliente\":1,\"idFuncion\":1,\"idsAsientos\":[2]}"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.sala", Matchers.is("S1")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.asientosOcupados.size()", Matchers.is(0)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.asientosReservados.size()", Matchers.is(1)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.compraID", Matchers.is(2)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.cliente", Matchers.is("user")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.clienteID", Matchers.is(1)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.pelicula", Matchers.is("The Avengers")));
    }
}
