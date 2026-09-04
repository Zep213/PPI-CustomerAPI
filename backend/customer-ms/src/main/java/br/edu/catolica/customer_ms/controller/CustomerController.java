package br.edu.catolica.customer_ms.controller;

import br.edu.catolica.customer_ms.constants.CustomerConstants;
import br.edu.catolica.customer_ms.dto.CustomerDTO;
import br.edu.catolica.customer_ms.dto.ResponseDTO;
import br.edu.catolica.customer_ms.dto.SellerProductsDTO;
import br.edu.catolica.customer_ms.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.edu.catolica.customer_ms.constants.CustomerConstants.*;

@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customers", description = "Recursos para gerenciamento de customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Cadastra cliente", description = "Cadastra um customer na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = CUSTOMER_MESSAGE_201),
            @ApiResponse(responseCode = "400", description = CUSTOMER_MESSAGE_400),
            @ApiResponse(responseCode = "500", description = CUSTOMER_MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(CUSTOMER_MESSAGE_201, HttpStatus.CREATED.value()));
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerProductsDTO> findProductsBySeller(@PathVariable("sellerId")Long sellerId){
       return ResponseEntity.status(HttpStatus.OK)
               .body(customerService.findProductsBySellerId(sellerId));
    }
}
