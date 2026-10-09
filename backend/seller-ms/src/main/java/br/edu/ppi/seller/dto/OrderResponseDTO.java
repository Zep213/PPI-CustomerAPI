package br.edu.ppi.seller.dto;

import br.edu.ppi.seller.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO (
        @NotBlank
        String orderCode,

        @JsonProperty("items")
        List<ItemOrderDTO> items,

        @NotBlank
        @JsonProperty("Estabelecimento")
        String sellerName,

        @Positive
        @JsonProperty("Total do pedido")
        BigDecimal amount,

        @NotBlank
        @JsonProperty("Status do pedido")
        OrderStatus orderStatus
){
}
