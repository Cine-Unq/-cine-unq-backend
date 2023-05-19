package com.cineunq;

import com.cineunq.dominio.Usuario;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    private Usuario u1;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        u1 = usuarioService.saveCliente(Usuario.builder().nombre("Pepe").correo("algo").password("Pepe1").roles(new ArrayList<>()).build());
    }

    @Test
    public void testUsuarioPorIdCuandoSoloExisteUno(){
        Usuario uTest = usuarioService.findByID(1L);
        assertEquals(u1.getId(),uTest.getId());
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
