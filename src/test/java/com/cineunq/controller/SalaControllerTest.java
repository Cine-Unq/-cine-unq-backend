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
public class SalaControllerTest {

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
    public void testCuandoExisteUnaSala() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/salas/1").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreSala", Matchers.is("S1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantFilas", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.columnas", Matchers.is("ABCD")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoSala", Matchers.is("2D")));
    }
}
