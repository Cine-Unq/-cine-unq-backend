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
public class FuncionControllerTest {

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
    public void testCuandoExisteFuncionesPorPelicula() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/funcion/1").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tipo", Matchers.is("2D")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].horarios[0].id", Matchers.is(1)));
    }

    @Test
    public void testCuandoNoExisteFuncionesPorPelicula() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/funcion/10").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(0)));

    }


}
