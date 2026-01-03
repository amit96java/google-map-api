package com.amit.google_maps_java.service;

import com.amit.google_maps_java.dto.GeoCodeResponse;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class GeocodingService {
    private final GeoApiContext geoApiContext;

    public GeoCodeResponse geocode(String address) throws InterruptedException, IOException, ApiException {
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
        if(results == null || results.length == 0) {
            return null;
        }
        GeocodingResult result = results[0];
        GeoCodeResponse geoCodeResponse = new GeoCodeResponse();
        geoCodeResponse.setFormattedAddress(result.formattedAddress);
        geoCodeResponse.setLatitude(result.geometry.location.lat);
        geoCodeResponse.setLongitude(result.geometry.location.lng);
        geoCodeResponse.setPlaceId(result.placeId);
        //if partial match is false , than it is a complete match address.
        geoCodeResponse.setPartialMatch(result.partialMatch);

        if(result.types != null) {
            List<String> types = Arrays.stream(result.types)
                    .map(AddressType::toCanonicalLiteral)
                    .toList();
            geoCodeResponse.setTypes(types);
        }

        return geoCodeResponse;
    }
}
