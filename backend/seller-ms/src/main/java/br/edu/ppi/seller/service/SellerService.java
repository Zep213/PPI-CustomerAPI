package br.edu.ppi.seller.service;

import br.edu.ppi.seller.domain.Seller;
import br.edu.ppi.seller.dto.SellerDTO;
import br.edu.ppi.seller.dto.SellerProductsDTO;
import br.edu.ppi.seller.exception.SellerException;
import br.edu.ppi.seller.mapper.ProductMapper;
import br.edu.ppi.seller.mapper.SellerMapper;
import br.edu.ppi.seller.repository.ProductRepository;
import br.edu.ppi.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void save(SellerDTO sellerDTO){
        try{
            Seller seller = sellerMapper.dtoToEntity(sellerDTO);
            sellerRepository.save(seller);
        } catch (Exception e) {
            log.error("m=save, error to try save seller with name = {} ", sellerDTO.name());
            throw new SellerException(e.getMessage());
        }
    }

   @Transactional(readOnly = true)
    public SellerProductsDTO sellerProductsDTO(Long sellerId){
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> {
                    log.warn("m=sellerProductsDTO, seller not found to id = {}", sellerId);
                    return new SellerException("Seller não encontrado");
        });
        var products = productRepository.findBySellerId(sellerId);

        return new SellerProductsDTO(seller.getName(), productMapper.entityToDTOList(products));
    }

}
