package br.edu.catolica.customer_ms.service;

import br.edu.catolica.customer_ms.client.SellerServiceClient;
import br.edu.catolica.customer_ms.domain.Customer;
import br.edu.catolica.customer_ms.dto.CustomerDTO;
import br.edu.catolica.customer_ms.dto.SellerProductsDTO;
import br.edu.catolica.customer_ms.exception.CustomerException;
import br.edu.catolica.customer_ms.mapper.CustomerMapper;
import br.edu.catolica.customer_ms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final SellerServiceClient sellerServiceClient;

    public void save(CustomerDTO customerDTO){
        try{
            Customer customer = customerMapper.dtoToEntity(customerDTO);
            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("m=save, error to try save customer with cpf = {} ", customerDTO.cpf());
            throw new CustomerException(e.getMessage());
        }
    }

    public SellerProductsDTO findProductsBySellerId(Long sellerId){
        return sellerServiceClient.getProductBySellerId(sellerId);
    }
}
