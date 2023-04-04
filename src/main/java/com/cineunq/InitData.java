package com.cineunq;

import com.cineunq.dao.AsientosRepository;
import com.cineunq.dao.ClienteRepository;
import com.cineunq.dao.CompraRepository;
import com.cineunq.dominio.Asiento;
import com.cineunq.dominio.Cliente;
import com.cineunq.dominio.Compra;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.dominio.Pelicula;
import com.cineunq.dominio.builder.AsientoBuilder;
import com.cineunq.dominio.builder.PeliculaBuilder;
import com.cineunq.service.AsientoService;
import com.cineunq.service.CompraService;
import com.cineunq.service.PeliculaService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    private void initialize() {
            logger.info("Init Data Using MySql DB");
            fireInitialData();
    }

    private List<Asiento> crearAsientos(){
        String letras = "ABCDEFGHIJ";
        List<Asiento> asientos = new ArrayList<>();
        for (int i = 0; i < 4;i++){ //Para las Columnas
            for(int j = 0; j < 4;j++){ //Para las filas
                Asiento a = new AsientoBuilder().withEstaOcupado(EstadoAsiento.LIBRE).withNrColumna(Character.toString(letras.charAt(i))).withNrFila(Integer.toString(j)).build();
                asientos.add(a);
            }
        }
        //return asientos.stream().map(asiento -> asientoService.saveAsiento(asiento)).toList();
        return asientos;
    }

    private void fireInitialData() {
        List<Asiento> asientosCreados = crearAsientos();
        Pelicula p = new PeliculaBuilder().withNombre("The Avengers").withDescripcion("El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.").withDuracion(150).withImagen("https://http2.mlstatic.com/D_NQ_NP_888996-MLA32569507268_102019-O.jpg").withAsientos(asientosCreados).build();
        peliculaService.savePelicula(p);
        Cliente pepe = new Cliente("Pepe","pepeArgento@gmail.com.ar");
        clienteRepository.save(pepe);
        compraService.saveCompra(1L,1L,List.of(1L,2L,3L,4L,5L,6L,7L,8L));
    }

}
