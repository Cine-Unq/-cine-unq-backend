package com.cineunq;

import com.cineunq.dao.AsientosRepository;
import com.cineunq.dao.ClienteRepository;
import com.cineunq.dao.CompraRepository;
import com.cineunq.dominio.*;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.dominio.builder.AsientoBuilder;
import com.cineunq.dominio.builder.PeliculaBuilder;
import com.cineunq.service.*;
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

    private PeliculaService peliculaService;

    private CompraService compraService;

    private ClienteService clienteService;

    private SalaService salaService;

    @Autowired
    public InitData(PeliculaService peliculaService,CompraService compraService, ClienteService clienteService, SalaService salaService) {
        this.peliculaService = peliculaService;
        this.compraService = compraService;
        this.clienteService = clienteService;
        this.salaService = salaService;
    }

    @PostConstruct
    private void initialize() {
            logger.info("Init Data Using MySql DB");
            fireInitialData();
    }

    private List<Asiento> crearAsientosV1(){
        String letras = "ABCDEFGHIJkLMN";
        List<Asiento> asientos = new ArrayList<>();
        for (int i = 0; i < 14;i++){ //Para las Columnas
            for(int j = 1; j < 5;j++){ //Para las filas
                Asiento a = new AsientoBuilder().withEstaOcupado(EstadoAsiento.LIBRE).withNrColumna(Character.toString(letras.charAt(i))).withNrFila(Integer.toString(j)).build();
                asientos.add(a);
            }
        }
        return asientos;
    }

    private void fireInitialData(){
        List<Asiento> asientosCreados = crearAsientosV1();
        List<Asiento> asientosCreados1 = crearAsientosV1();
        //List<Asiento> asientosCreados2 = crearAsientosV1();

        Cliente pepe = new Cliente("Pepe","pepeArgento@gmail.com.ar");
        Cliente coki = new Cliente("Coki","cokiArgento@gmail.com.ar");
        this.clienteService.saveCliente(pepe);
        this.clienteService.saveCliente(coki);

        Pelicula p = new PeliculaBuilder().withNombre("The Avengers").withDescripcion("El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.").withDuracion(150).withImagen("https://http2.mlstatic.com/D_NQ_NP_888996-MLA32569507268_102019-O.jpg").build();
        this.peliculaService.savePelicula(p);

        Pelicula p1 = new PeliculaBuilder().withNombre("John Wick").withDescripcion("Una exploración de las aventuras, las desgarradoras experiencias y las hazañas del legendario asesino a sueldo, John Wick.").withDuracion(200).withImagen("https://es.web.img3.acsta.net/pictures/14/10/01/14/18/135831.jpg").build();
        this.peliculaService.savePelicula(p1);

        Pelicula p2 = new PeliculaBuilder().withNombre("Evil Dead").withDescripcion("En una misteriosa y aislada cabaña, un grupo de adolescentes resucita por accidente a unas fuerzas demoníacas con un conjuro.").withDuracion(85).withImagen("https://i.pinimg.com/564x/66/bc/d5/66bcd5f5b359e1d830967fdabbd0d5b2.jpg").build();
        this.peliculaService.savePelicula(p2);


        Funcion f1 = Funcion.builder().peliculaEnFuncion(p).horaInicio(LocalDate.now()).horaFin(LocalDate.of(2023,12,31)).build();
        Sala s1 = Sala.builder().asientosSala(asientosCreados).nombreSala("1").funcion(f1).build();
        this.salaService.saveSala(s1);

        Funcion f2 = Funcion.builder().peliculaEnFuncion(p1).horaInicio(LocalDate.now()).horaFin(LocalDate.of(2023,12,31)).build();
        Sala s2 = Sala.builder().asientosSala(asientosCreados1).nombreSala("2").funcion(f2).build();
        this.salaService.saveSala(s1);
        this.salaService.saveSala(s2);

        Funcion f3 = Funcion.builder().peliculaEnFuncion(p1).horaInicio(LocalDate.now()).horaFin(LocalDate.of(2023,12,31)).build();
        Sala s3 = Sala.builder().asientosSala(asientosCreados1).nombreSala("3").funcion(f3).build();

        this.compraService.saveCompra(1L,1L);
        this.compraService.saveCompra(2L,2L);
    }

}
