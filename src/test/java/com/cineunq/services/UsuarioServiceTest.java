package com.cineunq.services;

import com.cineunq.dominio.Usuario;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void testUsuarioPorIdCuandoSoloExisteUno(){
        Usuario uTest = usuarioService.findByID(1L);
        assertEquals(1L,uTest.getId());
        assertEquals("Pepe",uTest.getNombre());
        assertEquals("user",uTest.getCorreo());
        assertTrue(passwordEncoder.matches("user", uTest.getPassword()));
        assertEquals("USER",uTest.getRoles().get(0).getName());
    }

    @Test
    public void testDondeNoExistePeliculaPorIdYTiraExcepcion(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Usuario uTest = usuarioService.findByID(10L);
        });

        String expectedMessage = "No se a encontrado el usuario solicitado ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
