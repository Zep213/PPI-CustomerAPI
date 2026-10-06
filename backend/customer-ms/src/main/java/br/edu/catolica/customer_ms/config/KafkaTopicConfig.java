package br.edu.catolica.customer_ms.config;

import br.edu.catolica.customer_ms.constants.TopicConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static br.edu.catolica.customer_ms.constants.TopicConstants.ORDER_CREATED;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderCreateTopic(){
        return TopicBuilder.name(ORDER_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

}
