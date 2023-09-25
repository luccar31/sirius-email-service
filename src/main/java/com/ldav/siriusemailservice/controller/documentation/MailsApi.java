package com.ldav.siriusemailservice.controller.documentation;

import com.ldav.siriusemailservice.domain.dto.LoginResponse;
import com.ldav.siriusemailservice.domain.dto.MailRequestInfo;
import com.ldav.siriusemailservice.domain.dto.MailResponseInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/emails")
public interface MailsApi {
    @Operation(summary = "Mandar mails", description = "Mandar mails con la información proporcionada")
    @ApiResponse(responseCode = "200", description = "Envio exitoso. Devuelve la información sobre el envío")
    @ApiResponse(responseCode = "400", description = "Cuerpo de la solicitud HTTP inválido")
    @ApiResponse(responseCode = "404", description = "No se encontraron proveedores válidos para mandar el mail")
    @ApiResponse(responseCode = "403", description = "No posee los permisos necesarios")
    @ApiResponse(responseCode = "403", description = "Cantidad de limite de mails para enviar por dia excedida")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MailResponseInfo> sendMail(
            @Parameter(description = "Información para el mail") @RequestBody @Valid MailRequestInfo emailInfo
    );

}
