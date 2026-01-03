package com.amit.google_maps_java.cache;

import com.amit.google_maps_java.dto.GeoCodeResponse;
import lombok.Data;

import java.time.Instant;

@Data
public class CachedGeocodeEntry {
    //    private GeoCodeResponse response;
    private Object response;

    private Instant expiresAt;

    public boolean isExpired() {
        return expiresAt.isBefore(Instant.now());
    }
}
