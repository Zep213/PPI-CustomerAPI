package br.edu.ppi.seller.messenger;

import br.edu.ppi.seller.constants.TopicConstants;
import br.edu.ppi.seller.dto.OrderCreatedEventDTO;
import br.edu.ppi.seller.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static br.edu.ppi.seller.constants.TopicConstants.ORDER_CREATED;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedListener {

    private final OrderService orderService;

    @KafkaListener(topics = ORDER_CREATED, groupId = "${spring.kafka.consumer.group-id}")
    public void saveAndResponseOrder(OrderCreatedEventDTO eventDTO){
        try {
            orderService.saveOrder(eventDTO);
        }catch (Exception e){
        log.error("m=saveAndResponseOrder, error to try consumer order-created to message = {}", eventDTO);
        throw new RuntimeException(e);
        }
    }
}
