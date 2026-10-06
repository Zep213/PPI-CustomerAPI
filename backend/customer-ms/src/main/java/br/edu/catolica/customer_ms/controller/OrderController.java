package br.edu.catolica.customer_ms.controller;

import br.edu.catolica.customer_ms.dto.OrderRequestDTO;
import br.edu.catolica.customer_ms.dto.ResponseDTO;
import br.edu.catolica.customer_ms.service.OrderEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderEventService orderEventService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO>
    createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        orderEventService.send(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO("Pedido enviado com sucesso",
                        201));
    }

}
