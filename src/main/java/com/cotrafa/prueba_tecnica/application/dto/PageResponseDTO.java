package com.cotrafa.prueba_tecnica.application.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResponseDTO<T>(
        long totalElements,
        int totalPages,
        int page,
        int size,
        List<T> content
) {}