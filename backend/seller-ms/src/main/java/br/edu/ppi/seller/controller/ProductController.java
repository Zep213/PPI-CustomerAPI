package br.edu.ppi.seller.controller;

import br.edu.ppi.seller.dto.ProductDTO;
import br.edu.ppi.seller.dto.ResponseDTO;
import br.edu.ppi.seller.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.edu.ppi.seller.constants.ProductConstants.*;
import static br.edu.ppi.seller.constants.ProductConstants.PRODUCT_MESSAGE_201;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Criar um produto", description = "Recurso para registrar um produto na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = PRODUCT_MESSAGE_201),
            @ApiResponse(responseCode = "400", description = PRODUCT_MESSAGE_400),
            @ApiResponse(responseCode = "500", description = PRODUCT_MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid ProductDTO productDTO){
        productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(PRODUCT_MESSAGE_201, HttpStatus.CREATED.value()));
    }
}
