package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con un mensaje de la operación")
public record MessageResponse(

        @Schema(
                description = "Mensaje de la operación",
                example = "Estado actualizado con éxito"
        )
        String message
) {
}