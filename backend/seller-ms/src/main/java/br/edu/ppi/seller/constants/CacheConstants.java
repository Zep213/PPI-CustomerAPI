package br.edu.ppi.seller.constants;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheConstants {

    public static  final  String KEY_PREFIX =  "seller:%d:products";
    public static  final Duration  KEY_TTL_30D = Duration.ofDays(30);
}
