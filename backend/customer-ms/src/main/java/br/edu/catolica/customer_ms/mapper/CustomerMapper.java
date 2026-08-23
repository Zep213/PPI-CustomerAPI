package br.edu.catolica.customer_ms.mapper;

import br.edu.catolica.customer_ms.domain.Customer;
import br.edu.catolica.customer_ms.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "address.id", ignore = true)
    Customer dtoToEntity(CustomerDTO customerDTO);

}
