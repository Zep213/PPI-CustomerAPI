package br.edu.ppi.seller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "SellerDTO", description = "Representação dos dados de um seller")
public record SellerDTO(
        @NotBlank(message = "O nome do vendedor é obrigatório")
        @Schema(name = "name", description = "Nome do vendedor")
        String name,

        @NotBlank(message = "O telefone do vendedor é obrigatório")
        @Schema(name = "phoneNumber", description = "Número do telefone do vendedor (somente números)", example = "83999555555")
        @Size(min = 11, max = 11, message = "O número do telefone deve conter 11 dígitos")
        String phoneNumber
) {
}
