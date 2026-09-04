package br.edu.catolica.customer_ms.dto;

import java.util.List;

public record SellerProductsDTO(
        String name,
        List<ProductDTO> products
) {
}
