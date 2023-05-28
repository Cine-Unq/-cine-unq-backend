package com.cineunq.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;


@Configuration
public class OpenAPIConfig {

    @Value("${movieUNQ.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("jonathan.a.gutierrez9@gmail.com");
        contact.setName("Jonathan");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Movie.Unq API")
                .version("1.0")
                .contact(contact)
                .description("API ofrecida para el manejo del servicio")
                .license(mitLicense);

        return new OpenAPI().addSecurityItem(new SecurityRequirement()
                .addList(securitySchemeName))
                .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"))).info(info).servers(List.of(devServer));
    }

//        return new OpenAPI().info(info).servers(List.of(devServer));
//    }
}
