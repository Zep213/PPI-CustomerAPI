package br.edu.ppi.seller.mapper;

import br.edu.ppi.seller.domain.Seller;
import br.edu.ppi.seller.dto.SellerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SellerMapper {

    @Mapping(target = "id", ignore = true)
    Seller dtoToEntity(SellerDTO sellerDTO);
}
