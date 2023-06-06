package com.cineunq.controller;

import com.cineunq.security.JwtGenerador;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class AsientoControllerTest {

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
    public void testCuandoNoExisteFuncion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/asientos/pelicula/funcion/10/").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(0)));
    }

    @Test
    public void testCuandoExisteFuncionAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/asientos/pelicula/funcion/1/").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    public void testCuandoNoExisteFuncionAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/asientos/pelicula/funcion/10/admin").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(0)));
    }

    @Test
    public void testCuandoExisteFuncion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/asientos/pelicula/funcion/1/").header("Authorization","Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    public void testAdminRegistraOcupadoUnAsiento() throws Exception {
        this.mockMvc.perform(post("/asientos/pelicula/funcion").header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idCliente\":1,\"idCompra\":1,\"asientos\":[1]}"))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
