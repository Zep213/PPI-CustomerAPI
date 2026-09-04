package br.edu.ppi.seller.mapper;

import br.edu.ppi.seller.domain.Product;
import br.edu.ppi.seller.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stock", ignore = true)
    Product dtoToEntity(ProductDTO productDTO);

    List<ProductDTO> entityToDTOList(List<Product> products);
}
