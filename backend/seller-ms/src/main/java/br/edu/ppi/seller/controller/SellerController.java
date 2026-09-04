package br.edu.ppi.seller.controller;

import br.edu.ppi.seller.dto.ResponseDTO;
import br.edu.ppi.seller.dto.SellerDTO;
import br.edu.ppi.seller.dto.SellerProductsDTO;
import br.edu.ppi.seller.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.edu.ppi.seller.constants.SellerConstants.*;


@RestController
@RequestMapping("api/v1/seller")
@Tag(name = "API de Seller", description = "API que contém os recursos para gerenciamento dos vendedores")
@AllArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("/create")
    @Operation(summary = "Regsitro de vendedor", description = "Recurso responsável por registrar um vendedo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = SELLER_MESSAGE_201),
            @ApiResponse(responseCode = "201", description = SELLER_MESSAGE_201),
            @ApiResponse(responseCode = "400", description = SELLER_MESSAGE_400),
            @ApiResponse(responseCode = "500", description = SELLER_MESSAGE_500),
    })
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid SellerDTO sellerDTO){
        sellerService.save(sellerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO( SELLER_MESSAGE_201, HttpStatus.CREATED.value()));
    }
    @GetMapping("/products/{sellerId}")
    public ResponseEntity<SellerProductsDTO> productsBySellerId(@PathVariable("sellerId") Long sellerId){
        var sellerProductsDTO = sellerService.sellerProductsDTO(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(sellerProductsDTO);
    }

}
