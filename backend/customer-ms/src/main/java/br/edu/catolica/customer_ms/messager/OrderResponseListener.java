package br.edu.catolica.customer_ms.messager;

import br.edu.catolica.customer_ms.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static br.edu.catolica.customer_ms.constants.TopicCostants.ORDER_RESPONSE;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderResponseListener {

    @KafkaListener(
            topics = ORDER_RESPONSE, groupId = "&{spring.kafka.consumer.group-id}"
    )
    public void orderResponse(OrderResponseDTO orderResponseDTO){
        log.info("m=orderResponse, mensagem recebida no topico = {} ", orderResponseDTO);
    }
}
