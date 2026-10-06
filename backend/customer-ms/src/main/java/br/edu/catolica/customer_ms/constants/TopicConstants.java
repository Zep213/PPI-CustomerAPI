package br.edu.catolica.customer_ms.constants;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TopicConstants {

    public static final String ORDER_CREATED = "order_created";
    public static final String ORDER_RESPONSE = "order_response";
}
