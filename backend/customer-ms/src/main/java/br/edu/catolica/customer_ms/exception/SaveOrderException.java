package br.edu.catolica.customer_ms.exception;

public class SaveOrderException extends RuntimeException {
    public SaveOrderException(String message) {
        super(message);
    }
}
