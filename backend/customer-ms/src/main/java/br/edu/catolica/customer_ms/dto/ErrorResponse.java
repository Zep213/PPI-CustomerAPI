package br.edu.catolica.customer_ms.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(String apiPath,
                            Integer httpStatus,
                            String message,
                            LocalDateTime errorTime) {
}
