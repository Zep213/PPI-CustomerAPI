package br.edu.catolica.customer_ms.cache;

import br.edu.catolica.customer_ms.client.SellerServiceClient;
import br.edu.catolica.customer_ms.dto.ProductDTO;
import br.edu.catolica.customer_ms.dto.SellerProductsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductCacheReader {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SellerServiceClient sellerServiceClient;

    public SellerProductsDTO getProdcutsBySeller(Long sellerId){
        String key = String.format("seller:%d:products", sellerId);

        SellerProductsDTO productCache = readFromCache(key);

        if(Objects.nonNull(productCache)){
            return  productCache;
        }

        var sellerProducts = sellerServiceClient.getProductBySellerId(sellerId);

        populateRedis(key, sellerProducts);

        return sellerProducts;
    }

    private SellerProductsDTO readFromCache(String key){
      try{
          Map<Object, Object>  productsFromSellerKey = redisTemplate.opsForHash().entries(key);
          if(productsFromSellerKey.isEmpty()){
              return null;
          }
          String sellerName = String.valueOf(productsFromSellerKey.remove("sellerName"));
          List<ProductDTO> products = productsFromSellerKey.values().stream()
                  .map( p-> (ProductDTO) p)
                  .toList();
          return new SellerProductsDTO(sellerName, products);

      } catch (Exception e) {
          log.warn("m=readFromCache, cache entry incompativel/corrompido para seller id = {}, descartando e buscando da API", extractSellerIdFromKey(key), e);
          redisTemplate.delete(key);
          return null;
      }
    }

    private void populateRedis(String key, SellerProductsDTO sellerProductsDTO){
        redisTemplate.opsForHash()
                .put(key, "sellerName", sellerProductsDTO.name());
        sellerProductsDTO.products().forEach(productDTO ->
                redisTemplate.opsForHash().put(key,"product:"+productDTO.id(), productDTO)
                );
        redisTemplate.expire(key, Duration.ofDays(30));
    }

    private String extractSellerIdFromKey(String key){
        return key.replace("seller:", "")
                .replace(":products", "");
    }

}
