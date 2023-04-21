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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData {

    protected final Log logger = LogFactory.getLog(getClass());

    private PeliculaService peliculaService;

    private CompraService compraService;

    private ClienteService clienteService;

    private SalaService salaService;

    private FuncionService funcionService;

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

    private List<Pelicula> crearPeliculas(){
        Pelicula p0 = new PeliculaBuilder().withNombre("The Avengers").withDescripcion("El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.").withDuracion(150).withImagen("https://http2.mlstatic.com/D_NQ_NP_888996-MLA32569507268_102019-O.jpg").build();

        Pelicula p1 = new PeliculaBuilder().withNombre("John Wick").withDescripcion("Una exploración de las aventuras, las desgarradoras experiencias y las hazañas del legendario asesino a sueldo, John Wick.").withDuracion(200).withImagen("https://es.web.img3.acsta.net/pictures/14/10/01/14/18/135831.jpg").build();

        Pelicula p2 = new PeliculaBuilder().withNombre("Evil Dead").withDescripcion("En una misteriosa y aislada cabaña, un grupo de adolescentes resucita por accidente a unas fuerzas demoníacas con un conjuro.").withDuracion(85).withImagen("https://i.pinimg.com/564x/66/bc/d5/66bcd5f5b359e1d830967fdabbd0d5b2.jpg").build();

        Pelicula p3 = new PeliculaBuilder().withNombre("Bastardos sin gloria").withDescripcion("Dos historias convergen. Una sigue a un grupo de soldados, cuya misión es matar nazis con la participación de una miembro de la resistencia alemana. La otra historia sigue a una joven judía que busca venganza por la muerte de su familia en manos de los nazis, y en cuyo cine va a reunirse la cúpula nazi en el estreno de una película.").withDuracion(153).withImagen("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQjfkx-qCim1KRKU9wxRYtduFju9D_oMsbKCOS8gdIyqBx732Ke").build();

        Pelicula p4 = new PeliculaBuilder().withNombre("El Padrino").withImagen("https://i.pinimg.com/originals/27/49/2a/27492a953f8ac7054cd3735bf8fd4da0.jpg").withDescripcion("El patriarca de una organización criminal transfiere el control de su clandestino imperio a su reacio hijo").withDuracion(175).build();

        List<Pelicula> peliculas = List.of(p0,p1,p2,p3,p4);
        peliculas.forEach(pelicula -> peliculaService.savePelicula(pelicula));
        return peliculas;
    }

    private List<Sala> crearSalas(){
        Sala s1 = Sala.builder().tipoSala("2D").nombreSala("S1").columnas("ABCDEFGHIJKLMN").cantFilas(5).build();
        Sala s2 = Sala.builder().tipoSala("3D").nombreSala("S1").columnas("ABCDEFGHIJKLMN").cantFilas(5).build();
        Sala s3 = Sala.builder().tipoSala("4D").nombreSala("S1").columnas("ABCDEFG").cantFilas(5).build();
        List<Sala> salas = List.of(s1,s2,s3);
        salas.forEach(sala -> this.salaService.saveSala(sala));
        return salas;
    }

    private void fireInitialData(){
        List<Pelicula> peliculas = crearPeliculas();
        List<Sala> salas = crearSalas();

        Cliente pepe = new Cliente("Pepe","pepeArgento@gmail.com.ar");
        Cliente coki = new Cliente("Coki","cokiArgento@gmail.com.ar");
        this.clienteService.saveCliente(pepe);
        this.clienteService.saveCliente(coki);

        Funcion f1 = Funcion.builder().peliculaEnFuncion(peliculas.get(0)).horaInicio(LocalDateTime.now()).sala(salas.get(0)).build();
        this.funcionService.saveFuncion(f1);

        Funcion f2 = Funcion.builder().peliculaEnFuncion(peliculas.get(1)).horaInicio(LocalDateTime.now()).sala(salas.get(1)).build();
        this.funcionService.saveFuncion(f2);

        Funcion f3 = Funcion.builder().peliculaEnFuncion(peliculas.get(2)).horaInicio(LocalDateTime.of(2023,4,21,22,00)).sala(salas.get(2)).build();
        this.funcionService.saveFuncion(f3);

        this.compraService.saveCompra(1L,1L);
        this.compraService.saveCompra(2L,2L);
    }

}
