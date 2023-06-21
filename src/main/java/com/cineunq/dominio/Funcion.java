package com.cineunq.dominio;

import com.cineunq.dominio.enums.EstadoAsiento;
import com.cineunq.dominio.filas.FilaT;
import com.cineunq.dominio.filas.SeccionT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    public Pelicula peliculaEnFuncion;

    public LocalDateTime horaInicio;

    public LocalDateTime horaFin;

    //TODO ver de usar CascadeType.All y removeOrphan
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JsonIgnore
    public List<Asiento> asientosSala;

    @ManyToOne
    private Sala sala;

    @Builder
    public Funcion(Pelicula peliculaEnFuncion, LocalDateTime horaInicio,Sala sala,ArrayList<Asiento> asientos) {
        this.peliculaEnFuncion = peliculaEnFuncion;
        this.horaInicio = horaInicio;
        this.horaFin = peliculaEnFuncion!=null ? horaInicio.plusMinutes(peliculaEnFuncion.getDuracion()) : null;
        this.sala = sala;
        this.asientosSala = asientos.isEmpty() ? crearAsientosV2(sala) : asientos;
    }

//    private List<Asiento> crearAsientos(Sala sala){
//        List<SeccionT> seccionTS = sala.getSecciones();
//        List<Asiento> asientos = new ArrayList<>();
//        for (int i = 0; i < sala.getColumnas().length();i++){ //Para las Columnas
//            for(int j = 1; j < sala.getCantFilas();j++){ //Para las filas
//                Asiento asiento = Asiento.builder().estado(EstadoAsiento.LIBRE).columna(Character.toString(sala.getColumnas().charAt(i))).fila(Integer.toString(j)).build();
//                asientos.add(asiento);
//            }
//        }
//        return asientos;
//    }

    private List<Asiento> crearAsientosV2(Sala sala){
        List<SeccionT> seccionTS = sala.getSecciones();
        Map<String,List<FilaT>> nombres = new HashMap<>();
        for (SeccionT st : seccionTS){
            for(FilaT filaT : st.getFilas()){
                if(nombres.get(filaT.getDescripcion()) != null){
                    nombres.get(filaT.getDescripcion()).add(filaT);
                }else{
                    List<FilaT> element = new ArrayList<>(Arrays.asList(filaT));
                    nombres.put(filaT.getDescripcion(),element);
                }
            }
        }
        return testAsiento(nombres);
    }

    private List<Asiento> testAsiento(Map<String, List<FilaT>> filas){
        ArrayList<Asiento> asientos = new ArrayList<>();
        for (Map.Entry<String, List<FilaT>> entry : filas.entrySet()) { //Recorro cada letra de fila "a","b","c" ,etc
            int cantidad = 1;
            for(FilaT f : entry.getValue()){ //Cada fila tiene una x cant de FilaT que cada una es por una seccion
                for(int i = 0; i < f.getCantAsientos();i++){
                    Asiento asiento = Asiento.builder().estado(EstadoAsiento.LIBRE).columna(entry.getKey()).fila(String.valueOf(cantidad)).build();
                    asientos.add(asiento);
                }
            }
        }
        return asientos;
    }

}
