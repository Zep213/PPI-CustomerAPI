package br.edu.catolica.customer_ms.service;

import br.edu.catolica.customer_ms.cache.ProductCacheReader;
import br.edu.catolica.customer_ms.domain.ItemOrder;
import br.edu.catolica.customer_ms.domain.Order;
import br.edu.catolica.customer_ms.dto.ProductDTO;
import br.edu.catolica.customer_ms.dto.OrderRequestDTO;
import br.edu.catolica.customer_ms.enums.OrderStatus;
import br.edu.catolica.customer_ms.exception.SaveOrderException;
import br.edu.catolica.customer_ms.mapper.OrderMapper;
import br.edu.catolica.customer_ms.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServicePersistence {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductCacheReader productCacheReader;

    public void saveOrder(OrderRequestDTO orderRequestDTO){
        try{
            Order order = orderMapper.dtoToEntity(orderRequestDTO);
            order.setStatus(OrderStatus.ANALYZE);
            orderRepository.save(order);
        } catch (Exception e) {
            log.error("m=saveOrder, failed to try save order, " +
                    "order code = {} ", orderRequestDTO.orderCode());
            throw new SaveOrderException(e.getMessage());
        }
    }


    public void updateOrderToFailed(String orderCode){
        orderRepository.findByOrderCode(orderCode)
                .ifPresentOrElse( order -> {
                    order.setStatus(OrderStatus.FAILED_TO_SEND);
                    orderRepository.save(order);
                },
         () -> log.warn("m=updateOrderToFailed," +
                 " order not found to order code = {}", orderCode));
    }




}
