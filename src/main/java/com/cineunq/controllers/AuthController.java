package com.cineunq.controllers;

import com.cineunq.controllers.dto.request.LoginRequest;
import com.cineunq.controllers.dto.request.RegistroRequest;
import com.cineunq.controllers.dto.response.AuthResponse;
import com.cineunq.dao.IRolesRepository;
import com.cineunq.dao.UsuarioRepository;
import com.cineunq.dominio.Roles;
import com.cineunq.dominio.Usuario;
import com.cineunq.security.JwtGenerador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRolesRepository rolesRepository;
    private UsuarioRepository usuariosRepository;
    private JwtGenerador jwtGenerador;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRolesRepository rolesRepository, UsuarioRepository usuariosRepository, JwtGenerador jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerador = jwtGenerador;
    }

    //Método para poder registrar usuarios con role "user"
    @PostMapping("register")
    public ResponseEntity<String> registrar(@Valid @RequestBody RegistroRequest dtoRegistro, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>("Error, campos invalidos", HttpStatus.BAD_REQUEST);
        }
        if (usuariosRepository.existsByCorreo(dtoRegistro.getCorreo())) {
            return new ResponseEntity<>("El usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setCorreo(dtoRegistro.getCorreo());
        usuario.setNombre(dtoRegistro.getNombre());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Roles roles = rolesRepository.findByName("USER").get();
        usuario.setRoles(roles);
        usuariosRepository.save(usuario);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.CREATED);
    }

    //Método para poder logear un usuario y obtener un token
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest dtoLogin, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>("Error, campos invalidos", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getMail(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }


}
