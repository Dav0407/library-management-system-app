package com.igriss.library_manager.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Davron Normamatov",
                        email = "Davronnormamatov004@gmail.com",
                        url = "https://github.com/Dav0407"
                ),
                description = "OpenAPI documentation for library management RESTful API",
                title = "Library manager application documentation - Davron Normamatov",
                version = "1.0.0",
                license = @License(
                        name = "License name",
                        url = "https://license-url.com"
                ),
                termsOfService = "Place for terms of service"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8080/api/v1"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "http://localhost:8088/api/v1"
                )
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }

)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
