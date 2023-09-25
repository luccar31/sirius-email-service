package com.ldav.siriusemailservice.controller.documentation;

import com.ldav.siriusemailservice.domain.dto.UserStats;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface StatsApi {
    @Operation(summary = "Obtiene estadisticas de usuarios", description = "Obtiene cuantos mails mando el usuario en el dia hasta el momento de la consulta. Solo puede consultar el administrador")
    @ApiResponse(responseCode = "403", description = "No posee los permisos necesarios")
    @ApiResponse(responseCode = "200")
    @GetMapping(value = "/stats")
    ResponseEntity<List<UserStats>> getStats();
}
