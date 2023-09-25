package com.ldav.siriusemailservice.controller.documentation;

import com.ldav.siriusemailservice.domain.dto.LoginRequest;
import com.ldav.siriusemailservice.domain.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/auth")
public interface AuthenticacionApi {
    @Operation(summary = "Iniciar sesión", description = "Inicia sesión con las credenciales proporcionadas")
    @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso. Devuelve un token JWT válido por 1 hora", content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse(responseCode = "400", description = "Error en la validación del cuerpo de la solicitud HTTP")
    @ApiResponse(responseCode = "403", description = "Credenciales incorrectas", content = {})
    @PostMapping(value = "/login")
    ResponseEntity<LoginResponse> login(
            @Parameter(description = "Solicitud de inicio de sesión") @Valid @RequestBody LoginRequest request
    );
}
