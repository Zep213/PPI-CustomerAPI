package br.edu.ppi.seller.dto;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ErrorResponseDTO(
        String apiPath,
        String message,
        Integer httpStatusCode,
        LocalDateTime errorTime
        ) {
}
