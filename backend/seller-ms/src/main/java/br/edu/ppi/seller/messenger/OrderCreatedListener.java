package br.edu.ppi.seller.messenger;

import br.edu.ppi.seller.constants.TopicConstants;
import br.edu.ppi.seller.dto.OrderCreatedEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static br.edu.ppi.seller.constants.TopicConstants.ORDER_CREATED;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedListener {

    @KafkaListener(topics = ORDER_CREATED, groupId = "${spring.kafka.consumer.group-id}")
    public void saveAndResponseOrder(OrderCreatedEventDTO eventDTO){
        log.info("Consumindo do tópico order-created, envento = {} ", eventDTO);
    }
}
