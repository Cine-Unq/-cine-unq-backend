package com.cineunq;

import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.builder.PeliculaBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class PeliculaTest{

    @Test
    public void testSizeAsientos(){
        Asiento a1 = new Asiento();
        Pelicula p1 = new PeliculaBuilder().withImagen("peli.png").withDuracion(100).withNombre("Pelicula Test").withDescripcion("Pelicula para test").withAsientos(List.of(a1)).build();
        assertEquals(p1.getAsientos().size(),1);
    }
}
