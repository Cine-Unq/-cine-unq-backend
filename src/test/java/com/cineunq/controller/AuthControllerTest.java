package com.cineunq.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void guardarUsuarioNuevo() throws Exception {
        this.mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"testUser\",\"correo\":\"testUser@gmail.com\"," +
                                "\"password\":\"testUserPass\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Registro de usuario exitoso"));
    }

    @Test
    public void guardarUsuarioNuevoPeroConCamposMal() throws Exception {

        this.mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\",\"correo\":\"testUser@gmail.com\"," +
                                "\"password\":\"testUserPass\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Error, campos invalidos"));
    }

    @Test
    public void guardarUsuarioNuevoConMailExistente() throws Exception {
        this.mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Pepe\",\"correo\":\"user\"," +
                                "\"password\":\"testUserPass\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("El usuario ya existe, intenta con otro"));
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mail\":\"user\",\"password\":\"user\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tokenType").value("Bearer "));


    }

    @Test
    public void loginPeroConCamposMal() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"maill\":\"user\",\"password\":\"user\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Error, campos invalidos"));

    }
}
