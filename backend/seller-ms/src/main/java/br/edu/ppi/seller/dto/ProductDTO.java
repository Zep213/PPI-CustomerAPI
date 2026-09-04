package br.edu.ppi.seller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(name = "ProductDTO", description = "Representação dos dados de um Produto")
public record ProductDTO(

        @Schema(description = "Id do produto", hidden = true)
        Long id,

        @Schema(description = "Nome do produto")
        @NotBlank(message = "O nome do produto é obrigatório")
        String description,

        @Positive(message = "O preço do produto está inválido")
        @Schema(description = "Valor do produto", example = "25.50")
        BigDecimal price,

        @Schema(description = "Id do vendedor")
        Long sellerId
) {
}
