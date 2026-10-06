package br.edu.catolica.customer_ms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(String topicName, String key, Object event){
        kafkaTemplate.send(topicName, key, event );
    }

}
