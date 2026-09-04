package br.edu.ppi.seller.service;

import br.edu.ppi.seller.cache.ProductCacheWriter;
import br.edu.ppi.seller.constants.SellerConstants;
import br.edu.ppi.seller.domain.Product;
import br.edu.ppi.seller.domain.Seller;
import br.edu.ppi.seller.dto.ProductDTO;
import br.edu.ppi.seller.exception.ProductException;
import br.edu.ppi.seller.exception.SellerException;
import br.edu.ppi.seller.mapper.ProductMapper;
import br.edu.ppi.seller.repository.ProductRepository;
import br.edu.ppi.seller.repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.edu.ppi.seller.constants.SellerConstants.SELLER_MESSAGE_204;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ProductMapper productMapper;
    private final ProductCacheWriter productCacheWriter;

    @Transactional
    public void save(ProductDTO productDTO){
      Seller seller = findSellerById(productDTO.sellerId());
      try{
          Product product = productMapper.dtoToEntity(productDTO);
          product.setSellerId(seller.getId());
          Product productSaved = productRepository.save(product);
          productCacheWriter.save(productSaved);
      } catch (Exception e) {
          log.error("m=save, error to try save product with description = {}," +
                  " to seller with id = {}  ", productDTO.description(), productDTO.sellerId());
          throw new ProductException(e.getMessage());
      }

    }

    private Seller findSellerById(Long id){
        return sellerRepository.findById(id)
                .orElseThrow(() ->{
                    log.warn("m=findSellerById, seller not fount by id = {} ", id);
                    return new SellerException(SELLER_MESSAGE_204);
                });
    }
}
