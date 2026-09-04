package br.edu.ppi.seller.dto;

import java.util.List;

public record SellerProductsDTO(
        String name,
        List<ProductDTO> products
) {
}
