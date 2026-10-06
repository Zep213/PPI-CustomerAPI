package br.edu.ppi.seller.dto;

import java.math.BigDecimal;

public record ItemOrderDTO(
        ProductDTO productDTO,
        BigDecimal quantity,
        BigDecimal subAmount
) {
}
