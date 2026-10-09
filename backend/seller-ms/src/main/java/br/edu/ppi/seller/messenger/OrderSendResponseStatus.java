package br.edu.ppi.seller.messenger;

import br.edu.ppi.seller.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static br.edu.ppi.seller.constants.TopicConstants.ORDER_RESPONSE;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderSendResponseStatus {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendResponseOrderStatus(OrderResponseDTO orderResponseDTO){
        try {
            kafkaTemplate.send(ORDER_RESPONSE, orderResponseDTO.orderCode(), orderResponseDTO);
        } catch (Exception e) {
            log.error("m=sendResponseOrderStatus, error to send event to order response topic");
            throw new RuntimeException(e);
        }
    }
}
