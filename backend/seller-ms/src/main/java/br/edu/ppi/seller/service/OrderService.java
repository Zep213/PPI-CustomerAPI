package br.edu.ppi.seller.service;

import br.edu.ppi.seller.domain.Order;
import br.edu.ppi.seller.domain.Product;
import br.edu.ppi.seller.dto.ItemOrderDTO;
import br.edu.ppi.seller.dto.OrderCreatedEventDTO;
import br.edu.ppi.seller.exception.ProductException;
import br.edu.ppi.seller.mapper.OrderMapper;
import br.edu.ppi.seller.repository.OrderRepository;
import br.edu.ppi.seller.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static br.edu.ppi.seller.constants.ProductConstants.PRODUCT_MESSAGE_204;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public void saveOrder(OrderCreatedEventDTO eventDTO){

        if(orderRepository.existsByOrderCode(eventDTO.orderCode())){
            log.warn("m=saveOrder order already with order code = {}",
                    eventDTO.orderCode());
        }
        Order order = orderMapper.orderEventDtoToEntity(eventDTO);

    }

    private List<ItemOrderDTO> buildItemsOrderDTO(Order order){
        return order.getItems()
                .stream().map(itemOrder -> {
                    Product product = productRepository.findById(itemOrder.getProductId())
                            .orElseThrow(() -> {
                                log.warn("m=buildItemsOrderDTO, product not found, id = " +
                                        "{}", itemOrder.getProductId());
                                return new ProductException(PRODUCT_MESSAGE_204);
                            });
                    itemOrder.setOrder(order);
                    itemOrder.setSubAmount(product.getPrice()
                            .multiply(BigDecimal.valueOf(itemOrder.getQuantity())));
                    return orderMapper.toItemOrderDTO(itemOrder, product);
                }).toList();
    }

}
