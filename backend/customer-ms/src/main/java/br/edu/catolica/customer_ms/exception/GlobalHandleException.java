package br.edu.catolica.customer_ms.exception;




import br.edu.catolica.customer_ms.dto.ErrorResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;


@RestControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errorsMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e ->{
            String fieldError = (e instanceof FieldError field) ? field.getField() : e.getObjectName();
            errorsMap.put(fieldError, e.getDefaultMessage());
        } );
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> handleCustomerErrorException(CustomerException ex, WebRequest req){
        var errorResponse = ErrorResponse.builder()
                .apiPath(req.getDescription(false))
                .message(ex.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorTime(LocalDateTime.now())
                .build();

        return  new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
