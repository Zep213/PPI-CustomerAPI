package br.edu.catolica.customer_ms.dto;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(
        Long sellerId,
        Long customerId,
        String orderCode,
        List<ItemRequestDTO> items
) {
    public OrderRequestDTO(Long sellerId,
                              Long customerId,
                              List<ItemRequestDTO> items){
        this(sellerId, customerId, UUID.randomUUID().toString(), items);
    }
}
