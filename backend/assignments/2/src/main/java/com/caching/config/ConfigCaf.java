package com.caching.config;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
import static java.util.Arrays.asList;  // Import from java.util.Arrays
@Configuration
@EnableCaching
public class ConfigCaf {
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(3)  // after it reaches the maxsize it will evict in LRU fashion
                .expireAfterAccess(30, TimeUnit.MINUTES); // time bases eviction
    }
    @Bean("caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeineConfig());
        caffeineCacheManager.setCacheNames(asList("geocoding", "reverse-geocoding"));
        return caffeineCacheManager;
    }
}