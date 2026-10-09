package br.edu.ppi.seller.mapper;

import br.edu.ppi.seller.domain.ItemOrder;
import br.edu.ppi.seller.domain.Order;
import br.edu.ppi.seller.domain.Product;
import br.edu.ppi.seller.domain.Seller;
import br.edu.ppi.seller.dto.ItemOrderDTO;
import br.edu.ppi.seller.dto.OrderCreatedEventDTO;
import br.edu.ppi.seller.dto.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    Order orderEventDtoToEntity(OrderCreatedEventDTO eventDTO);

    ItemOrderDTO toItemOrderDTO(ItemOrder itemOrder, Product product);

    OrderResponseDTO toResponseDTO(Order order, Seller seller, List<ItemOrderDTO> items);

}
