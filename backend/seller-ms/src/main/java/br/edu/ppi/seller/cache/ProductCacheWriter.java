package br.edu.ppi.seller.cache;

import br.edu.ppi.seller.constants.CacheConstants;
import br.edu.ppi.seller.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static br.edu.ppi.seller.constants.CacheConstants.KEY_PREFIX;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductCacheWriter {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(Product product){
        try{
            String key = String.format(KEY_PREFIX, product.getSellerId());
            redisTemplate.opsForHash().put(key, "product:"+product.getId(), product);
            redisTemplate.expire(key, CacheConstants.KEY_TTL_30D);
        } catch (Exception e) {
           log.warn("m=save, failed to try save product = {}  on Redis", product.getId());
        }
    }

}


