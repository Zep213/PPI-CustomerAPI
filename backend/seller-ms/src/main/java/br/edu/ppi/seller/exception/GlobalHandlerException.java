package br.edu.ppi.seller.exception;

import br.edu.ppi.seller.dto.ErrorResponseDTO;
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
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var errorsMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach( e -> {
            String errorField = (e instanceof FieldError field) ? field.getField() : e.getObjectName();
            errorsMap.put(errorField, e.getDefaultMessage());
        });

        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ErrorResponseDTO> handleSellerInternalError(SellerException ex, WebRequest req){
        var errorResponse = ErrorResponseDTO.builder()
                .apiPath(req.getDescription(false))
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductInternalErrorException(ProductException ex, WebRequest req){
        var errorResponseDTO = ErrorResponseDTO.builder()
                .errorTime(LocalDateTime.now())
                .message(ex.getMessage())
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .apiPath(req.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
