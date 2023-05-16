package com.cineunq.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
//Le indica al contenedor de spring que esta es una clase de seguridad al momento de arrancar la aplicación
@EnableWebSecurity
//Indicamos que se activa la seguridad web en nuestra aplicación y además esta será una clase la cual contendrá toda la configuración referente a la seguridad
public class SecurityConfig {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    //Este bean va a encargarse de verificar la información de los usuarios que se loguearán en nuestra api
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Con este bean nos encargaremos de encriptar todas nuestras contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Este bean incorporará el filtro de seguridad de json web token que creamos en nuestra clase anterior
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "HEAD", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors.applyPermitDefaultValues());
        return source;
    }

    //Vamos a crear un bean el cual va a establecer una cadena de filtros de seguridad en nuestra aplicación.
    // Y es aquí donde determinaremos los permisos segun los roles de usuarios para acceder a nuestra aplicación
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .exceptionHandling() //Permitimos el manejo de excepciones
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //Nos establece un punto de entrada personalizado de autenticación para el manejo de autenticaciones no autorizadas
                .and()
                .sessionManagement() //Permite la gestión de sessiones
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() //Toda petición http debe ser autorizada
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/peliculas/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.POST,"/compra/").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/funcion/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/asientos/**/").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/asientos/pelicula/funcion/**/admin").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET,"/compra/**","/salas/**").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST,"/asientos/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE, "/asientos/**","/compra/**","/funcion/**","/peliculas/**","/salas/**").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/asientos/**","/compra/**","/funcion/**","/peliculas/**","/salas/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        //http.headers().frameOptions().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
