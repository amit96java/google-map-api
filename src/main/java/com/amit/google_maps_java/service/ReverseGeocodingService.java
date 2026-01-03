package com.amit.google_maps_java.service;

import com.amit.google_maps_java.cache.CachedGeocodeEntry;
import com.amit.google_maps_java.cache.GeocodeCacheStore;
import com.amit.google_maps_java.dto.GeoCodeResponse;
import com.amit.google_maps_java.dto.ReverseGeocodeResponse;
import com.amit.google_maps_java.model.LocationUsability;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor

public class ReverseGeocodingService {
    private final GeoApiContext context;
    private final GeocodeCacheStore cacheStore;


    public ReverseGeocodeResponse reverse(double rawLat, double rawLng) throws Exception {
        double lat = normalize(rawLat);
        double lng = normalize(rawLng);

        String cacheKey = buildCacheKey(lat, lng);
        CachedGeocodeEntry cached = cacheStore.get(cacheKey);

        if(cached != null && !cached.isExpired()) {
            return (ReverseGeocodeResponse) cached.getResponse();
        }

        LatLng location = new LatLng(lat, lng);

        GeocodingResult[] results =
                GeocodingApi.reverseGeocode(context, location).await();

        if (results == null || results.length == 0) {
            return null;
        }
        var result = results[0];

        String accuracy = result.geometry.locationType.toString();

        ReverseGeocodeResponse response = new ReverseGeocodeResponse();
        response.setFormattedAddress(result.formattedAddress);
        response.setPlaceId(result.placeId);
        response.setLocationType(result.geometry.locationType.toString());
        response.setUsability(evaluateLocation(accuracy));

        CachedGeocodeEntry entry = new CachedGeocodeEntry();
        entry.setResponse(response);
        entry.setExpiresAt(Instant.now().plusSeconds(3600));
        cacheStore.put(cacheKey, entry);

        return response;
    }

    private double normalize(double value) {
        return Math.round(value * 1_00000d) / 1_00000d;
    }

    private String buildCacheKey(double lat, double lng) {
        return lat + ":" + lng;
    }

    private LocationUsability evaluateLocation(String accuracy) {
        if (accuracy == null) {
            return LocationUsability.UNUSABLE;
        }

        return switch (accuracy) {
            case "ROOFTOP" -> LocationUsability.PRECISE_DELIVERY;
            case "RANGE_INTERPOLATED" -> LocationUsability.ROUTING_ONLY;
            case "GOEMETRIC_CENTER", "APPROXIMATE" -> LocationUsability.CITY_LEVEL_ONLY;
            default -> LocationUsability.UNUSABLE;
        };
    }
}
