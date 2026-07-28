package com.cotrafa.prueba_tecnica.application.dto;

import lombok.Builder;

@Builder
public record UpdateStateDTO(
        Long idLoan,
        Long idState
) {
}
