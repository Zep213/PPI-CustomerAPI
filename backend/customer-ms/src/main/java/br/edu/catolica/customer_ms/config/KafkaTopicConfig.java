package br.edu.catolica.customer_ms.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static br.edu.catolica.customer_ms.constants.TopicCostants.ORDER_CREATED;
import static br.edu.catolica.customer_ms.constants.TopicCostants.ORDER_RESPONSE;


@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderCreateTopic(){
        return TopicBuilder.name(ORDER_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderResponseTopic(){
        return TopicBuilder.name(ORDER_RESPONSE)
                .partitions(3)
                .replicas(1)
                .build();
    }


}
