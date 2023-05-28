package com.cineunq.controller;

import com.cineunq.security.JwtGenerador;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtGenerador jwtProvider;

    private String token;

    @BeforeEach
    public void setUp() {
        this.token = this.jwtProvider.generarTokenByUsername("admin");
    }

    @Test
    public void testCuandoSoloExisteUnaPelicula() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", Matchers.is("The Avengers")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descripcion", Matchers.is("Avengers")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].duracion", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].imagen", Matchers.is("avengers.png")));
    }

    @Test
    public void testCuandoSoloExisteUnaPeliculaYTraigoEsaPelicula() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/1").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is("The Avengers")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion", Matchers.is("Avengers")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duracion", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imagen", Matchers.is("avengers.png")));
    }
}
