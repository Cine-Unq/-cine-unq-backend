package com.cineunq;


import com.cineunq.dao.IRolesRepository;
import com.cineunq.dominio.*;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.*;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${database}")
    private String className;

    private PeliculaService peliculaService;

    private CompraService compraService;

    private UsuarioService usuarioService;

    private SalaService salaService;

    private FuncionService funcionService;

    private IRolesRepository rolesRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitData(PeliculaService peliculaService, CompraService compraService, UsuarioService usuarioService, SalaService salaService, FuncionService funcionService,IRolesRepository rolesRepository,PasswordEncoder passwordEncoder) {
        this.peliculaService = peliculaService;
        this.compraService = compraService;
        this.usuarioService = usuarioService;
        this.salaService = salaService;
        this.funcionService = funcionService;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initialize() throws NotFoundException {
        if(className.equals("prod")){
            logger.info("Init Data Using MySql DB");
            fireInitialData();
        }
    }

    private List<Pelicula> crearPeliculas(){
        Pelicula p0 = Pelicula.builder().nombre("The Avengers").descripcion("El director de la Agencia SHIELD decide reclutar a un equipo para salvar al mundo de un desastre casi seguro cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial.").duracion(150).imagen("https://http2.mlstatic.com/D_NQ_NP_888996-MLA32569507268_102019-O.jpg").build();

        Pelicula p1 = Pelicula.builder().nombre("John Wick").descripcion("Una exploración de las aventuras, las desgarradoras experiencias y las hazañas del legendario asesino a sueldo, John Wick.").duracion(200).imagen("https://es.web.img3.acsta.net/pictures/14/10/01/14/18/135831.jpg").build();

        Pelicula p2 = Pelicula.builder().nombre("Evil Dead").descripcion("En una misteriosa y aislada cabaña, un grupo de adolescentes resucita por accidente a unas fuerzas demoníacas con un conjuro.").duracion(85).imagen("https://i.pinimg.com/564x/66/bc/d5/66bcd5f5b359e1d830967fdabbd0d5b2.jpg").build();

        Pelicula p3 = Pelicula.builder().nombre("Bastardos sin gloria").descripcion("Dos historias convergen. Una sigue a un grupo de soldados, cuya misión es matar nazis con la participación de una miembro de la resistencia alemana. La otra historia sigue a una joven judía que busca venganza por la muerte de su familia en manos de los nazis, y en cuyo cine va a reunirse la cúpula nazi en el estreno de una película.").duracion(153).imagen("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQjfkx-qCim1KRKU9wxRYtduFju9D_oMsbKCOS8gdIyqBx732Ke").build();

        Pelicula p4 = Pelicula.builder().nombre("El Padrino").imagen("https://i.pinimg.com/originals/27/49/2a/27492a953f8ac7054cd3735bf8fd4da0.jpg").descripcion("El patriarca de una organización criminal transfiere el control de su clandestino imperio a su reacio hijo").duracion(175).build();

        List<Pelicula> peliculas = List.of(p0,p1,p2,p3,p4);
        peliculas.forEach(pelicula -> peliculaService.savePelicula(pelicula));
        return peliculas;
    }

    private List<Sala> crearSalas(){
        Sala s1 = Sala.builder().tipoSala("2D").nombreSala("S1").columnas("ABCDEFGHIJKLMN").cantFilas(5).build();
        Sala s2 = Sala.builder().tipoSala("3D").nombreSala("S2").columnas("ABCDEFGHIJKLMN").cantFilas(5).build();
        Sala s3 = Sala.builder().tipoSala("4D").nombreSala("S3").columnas("ABCDEFG").cantFilas(5).build();
        List<Sala> salas = List.of(s1,s2,s3);
        salas.forEach(sala -> this.salaService.saveSala(sala));
        return salas;
    }

    private void fireInitialData() throws NotFoundException {

            Roles rolUser = new Roles(1L,"USER");
            Roles rolAdmin = new Roles(2L,"ADMIN");

            rolesRepository.save(rolUser);
            rolesRepository.save(rolAdmin);

            Usuario pepe = new Usuario(1L,"Pepe","pepeArgento@gmail.com.ar",passwordEncoder.encode("Pepe123"),List.of(rolUser));
            Usuario coki = new Usuario(2L,"Coki","cokiArgento@gmail.com.ar",passwordEncoder.encode("Coki123"),List.of(rolUser));
            Usuario guti = new Usuario(3L,"Guti","guti@gmail.com.ar",passwordEncoder.encode("Guti123"),List.of(rolAdmin));

            this.usuarioService.saveCliente(pepe);
            this.usuarioService.saveCliente(coki);
            this.usuarioService.saveCliente(guti);

            List<Pelicula> peliculas = crearPeliculas();
            List<Sala> salas = crearSalas();

            Funcion f1 = Funcion.builder().peliculaEnFuncion(peliculas.get(0)).horaInicio(LocalDateTime.now()).sala(salas.get(0)).asientos(new ArrayList<>()).build();
            this.funcionService.saveFuncion(f1,1L);

            Funcion f2 = Funcion.builder().peliculaEnFuncion(peliculas.get(1)).horaInicio(LocalDateTime.now()).sala(salas.get(1)).asientos(new ArrayList<>()).build();
            this.funcionService.saveFuncion(f2,2L);

//            Funcion f3 = Funcion.builder().peliculaEnFuncion(peliculas.get(2)).horaInicio(LocalDateTime.now().plusHours(4)).sala(salas.get(0)).build();
//            this.funcionService.saveFuncion(f3,1L);

            Funcion f4 = Funcion.builder().peliculaEnFuncion(peliculas.get(0)).horaInicio(LocalDateTime.now().plusHours(4)).sala(salas.get(1)).asientos(new ArrayList<>()).build();
            this.funcionService.saveFuncion(f4,1L);

            this.compraService.saveCompra(1L,1L,List.of(1L,2L,3L,4L,5L));
            this.compraService.saveCompra(2L,2L,List.of(57L,58L,59L));


    }

}
