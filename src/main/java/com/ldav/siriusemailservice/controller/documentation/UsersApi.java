package com.ldav.siriusemailservice.controller.documentation;

import com.ldav.siriusemailservice.domain.dto.SignupForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UsersApi {
    @Operation(summary = "Registro de nuevo usuario", description = "Registra un nuevo usuario con la información proporcionada")
    @ApiResponse(responseCode = "201", description = "Registro exitoso")
    @ApiResponse(responseCode = "400", description = "Error en validación de formulario")
    @PutMapping
    ResponseEntity<?> signup(@Parameter(description = "Formulario de registro de usuario") @Valid @RequestBody SignupForm form);
}
