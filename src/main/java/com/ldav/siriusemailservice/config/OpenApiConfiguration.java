package com.ldav.siriusemailservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Sirius Mail Service",
                contact = @Contact(
                        name = "Lucas David Cardozo",
                        email = "contact@ldav.space"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.APIKEY,
        scheme = "Bearer",
        in = SecuritySchemeIn.HEADER,
        paramName = "Authorization",
        bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfiguration {}
