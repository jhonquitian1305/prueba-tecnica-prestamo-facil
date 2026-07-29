package com.cotrafa.prueba_tecnica.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Datos para actualizar el estado de un préstamo")
@Builder
public record UpdateStateDTO(
        @Schema(
                description = "Identificador del préstamo",
                example = "15"
        )
        Long idLoan,

        @Schema(
                description = "Identificador del nuevo estado",
                example = "2"
        )
        Long idState
) {
}
