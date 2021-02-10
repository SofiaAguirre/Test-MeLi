package com.meli.fuegoquasar.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.meli.fuegoquasar.models.Satellite;
import org.springframework.stereotype.Component;

@Component
public class CacheConfiguration {

    public Cache<String, Satellite> satelliteCache = CacheBuilder.newBuilder()
            .recordStats()
            .maximumSize(3)
            .build();


    public CacheStats getSatelliteCacheStats(){
        return satelliteCache.stats();
    }
}
