package com.cineunq.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    public Long id;

    public String nombre;

    public String correo;

    private String password;

    @OneToOne()
//    @JoinTable(name = "usuario_roles",joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id_usuario"),
//            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id_role")
//    )
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    private Roles roles ;

    @Builder(access = AccessLevel.PUBLIC)
    public Usuario(String nombre, String correo, String password, Roles roles) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.roles = roles;
    }
}
