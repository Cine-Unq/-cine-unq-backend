package com.cineunq;


import com.cineunq.dao.IRolesRepository;
import com.cineunq.dao.InfoTipoSalaRepository;
import com.cineunq.dominio.*;
import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.dominio.enums.TipoSala;
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
import java.util.Collection;
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

    private InfoTipoSalaRepository infoTipoSalaRepository;

    @Autowired
    public InitData(PeliculaService peliculaService, CompraService compraService, UsuarioService usuarioService, SalaService salaService, FuncionService funcionService,IRolesRepository rolesRepository,PasswordEncoder passwordEncoder , InfoTipoSalaRepository infoTipoSalaRepository) {
        this.peliculaService = peliculaService;
        this.compraService = compraService;
        this.usuarioService = usuarioService;
        this.salaService = salaService;
        this.funcionService = funcionService;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.infoTipoSalaRepository = infoTipoSalaRepository;
    }

    @PostConstruct
    private void initialize(){
        if(className.equals("prod")){
            logger.info("Init Data Using MySql DB");
            fireInitialData();
        }else{
            logger.info("Init Data Using H2 DB");
            fireInitialDataBasicTest();
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

    private List<FormaSala> formaSalas1(){
        List<Integer> fila1 = List.of(5,6,7,8,9);
        List<Integer> fila2 = List.of(4,5,6,7,8,9,10);
        List<Integer> fila3 = List.of(3,4,5,6,7,8,9,10,11);
        List<Integer> fila4 = List.of();
        List<Integer> fila5 = List.of(4,5,6,7,8,9,10);
        List<Integer> fila6 = List.of(  0,1,2,4,5,6,7,8,9,10,12,13,14);
        List<Integer> fila7 = List.of(0,1,2,4,5,6,7,8,9,10,12,13,14);
        List<Integer> fila8 = List.of(0,1,2,4,5,6,7,8,9,10,12,13,14);
        List<Integer> fila9 = List.of(0,1,2,4,5,6,7,8,9,10,12,13,14);
        List<Integer> fila10 = List.of(0,1,2,4,5,6,7,8,9,10,12,13,14);
        List<Integer> fila11 = List.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        List<List<Integer>> matriz = List.of(fila1,fila2,fila3,fila4,fila5,fila6,fila7,fila8,fila9,fila10,fila11);
        Integer contador = 0;
        List<FormaSala> sala = new ArrayList<>();
        for (List<Integer> fila : matriz) {
            for (Integer columna : fila) {
                sala.add(FormaSala.builder().columnas(columna.toString()).fila(contador.toString()).build());
            }
            contador++;
        }
        return sala;
    }

    private List<FormaSala> formaSalas2(){
        List<Integer> fila1 = List.of(5,6,7,8,9,10);
        List<Integer> fila2 = List.of(4,5,6,7,8,9,10,11);
        List<Integer> fila3 = List.of(3,4,5,6,7,8,9,10,11,12);
        List<Integer> fila4 = List.of();
        List<Integer> fila5 = List.of(0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila6 = List.of(  0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila7 = List.of(0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila8 = List.of(0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila9 = List.of(0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila10 = List.of(0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila11 = List.of(0,1,2,4,5,6,7,8,9,10,11,13,14,15);
        List<Integer> fila12 = List.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        List<List<Integer>> matriz = List.of(fila1,fila2,fila3,fila4,fila5,fila6,fila7,fila8,fila9,fila10,fila11,fila12);
        Integer contador = 0;
        List<FormaSala> sala = new ArrayList<>();
        for (List<Integer> fila : matriz) {
            for (Integer columna : fila) {
                sala.add(FormaSala.builder().columnas(columna.toString()).fila(contador.toString()).build());
            }
            contador++;
        }
        return sala;
    }

    private List<Sala> crearSalas(){
        InfoTipoSala infoTipoSala2d = infoTipoSalaRepository.save(InfoTipoSala.builder().precio(100.0).tipoSala(TipoSala.DOS_D).build());
        InfoTipoSala infoTipoSala3d = infoTipoSalaRepository.save(InfoTipoSala.builder().precio(200.0).tipoSala(TipoSala.TRES_D).build());
        InfoTipoSala infoTipoSala4d = infoTipoSalaRepository.save(InfoTipoSala.builder().precio(300.0).tipoSala(TipoSala.CUATRO_D).build());

        List<FormaSala> sala1 = formaSalas1();
        List<FormaSala> sala2 = formaSalas2();

        Sala s1 = Sala.builder().tipoSala(infoTipoSala2d).nombreSala("S1").columnas("15").cantFilas(11).forma(sala1).build();
        Sala s2 = Sala.builder().tipoSala(infoTipoSala3d).nombreSala("S2").columnas("16").cantFilas(12).forma(sala2).build();
        //Sala s3 = Sala.builder().tipoSala(infoTipoSala4d).nombreSala("S3").columnas("4").cantFilas(4).build();

        List<Sala> salas = List.of(s1,s2);
        salas.forEach(sala -> this.salaService.saveSala(sala));
        return salas;
    }

    private void fireInitialData() {

            Roles rolUser = new Roles(1L,"USER");
            Roles rolAdmin = new Roles(2L,"ADMIN");

            rolUser= rolesRepository.save(rolUser);
            rolAdmin= rolesRepository.save(rolAdmin);

            Usuario pepe = Usuario.builder().nombre("Pepe").correo("user").password(passwordEncoder.encode("user")).roles(rolUser).build();
            Usuario coki = Usuario.builder().nombre("Coki").correo("user2").password(passwordEncoder.encode("user2")).roles(rolUser).build();
            Usuario guti = Usuario.builder().nombre("Guti").correo("admin").password(passwordEncoder.encode("admin")).roles(rolAdmin).build();

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
//
//            Funcion f4 = Funcion.builder().peliculaEnFuncion(peliculas.get(0)).horaInicio(LocalDateTime.now().plusHours(4)).sala(salas.get(1)).asientos(new ArrayList<>()).build();
//            this.funcionService.saveFuncion(f4,1L);

            this.compraService.saveCompra(1L,1L,List.of(1L,2L,3L,4L,5L));
            this.compraService.saveCompra(2L,2L,List.of(120L,121L,122L));
//
            compraService.confirmarPagoCompra(1L);
            compraService.confirmarPagoCompra(2L);


    }

    private void fireInitialDataBasicTest(){
        Roles rolUser = new Roles(1L,"USER");
        Roles rolAdmin = new Roles(2L,"ADMIN");

        InfoTipoSala infoTipoSala2d = infoTipoSalaRepository.save(InfoTipoSala.builder().precio(100.0).tipoSala(TipoSala.DOS_D).build());

        rolUser = rolesRepository.save(rolUser);
        rolAdmin = rolesRepository.save(rolAdmin);

        Usuario pepe = Usuario.builder().nombre("Pepe").correo("user").password(passwordEncoder.encode("user")).roles(rolUser).build();
        Usuario guti = Usuario.builder().nombre("Guti").correo("admin").password(passwordEncoder.encode("admin")).roles(rolAdmin).build();

        this.usuarioService.saveCliente(pepe);
        this.usuarioService.saveCliente(guti);

        Pelicula p0 = peliculaService.savePelicula(Pelicula.builder().nombre("The Avengers").descripcion("Avengers").duracion(150).imagen("avengers.png").build());

        Sala s1 = salaService.saveSala(Sala.builder().tipoSala(infoTipoSala2d).nombreSala("S1").columnas("ABCD").cantFilas(4).build());

        List<Asiento> asientos = List.of(Asiento.builder().estado(EstadoAsiento.LIBRE).columna("A").fila("1").build(),Asiento.builder().estado(EstadoAsiento.LIBRE).columna("A").fila("2").build());
        Funcion f1 = this.funcionService.saveFuncion(Funcion.builder().peliculaEnFuncion(p0).horaInicio(LocalDateTime.now()).sala(s1).asientos(new ArrayList<>(asientos)).build(),1L);
        //Funcion f2 = this.funcionService.saveFuncion(Funcion.builder().horaInicio(LocalDateTime.of(2024,1,1,12, 0)).sala(s1).asientos().build(),1L);

        Compra c1 = this.compraService.saveCompra(1L,1L,List.of(1L));
    }

}
