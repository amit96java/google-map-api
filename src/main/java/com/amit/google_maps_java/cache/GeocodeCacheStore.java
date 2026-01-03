package com.amit.google_maps_java.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class GeocodeCacheStore {
    private final Map<String, CachedGeocodeEntry> cache = new ConcurrentHashMap<>();

    public CachedGeocodeEntry get(String key) {
        return cache.get(key);
    }

    public void put(String key, CachedGeocodeEntry entry) {
        cache.put(key, entry);
    }
}
