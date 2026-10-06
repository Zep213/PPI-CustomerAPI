package br.edu.catolica.customer_ms.enums;

public enum OrderStatus {

    ANALYZE("Em análise"),
    ACCEPTED("Aceito"),
    PRODUCTION("Em produção"),
    REFUSED("Recusado"),
    CANCELED("Cancelado"),
    DELIVERY("Saiu para entrega"),
    FAILED_TO_SEND("Falha ao gerar pedido");

    private String value;

    OrderStatus(String value){
        this.value = value;
    }
}
