package br.edu.catolica.customer_ms.client;

import br.edu.catolica.customer_ms.dto.SellerProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "seller-service", url = "${seller-service.menu.url}")
public interface SellerServiceClient {

    @GetMapping("/{sellerId}")
    SellerProductsDTO getProductBySellerId(@PathVariable("sellerId") Long id);

}
