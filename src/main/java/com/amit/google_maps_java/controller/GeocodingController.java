package com.amit.google_maps_java.controller;

import com.amit.google_maps_java.dto.GeoCodeRequest;
import com.amit.google_maps_java.dto.GeoCodeResponse;
import com.amit.google_maps_java.dto.ReverseGeocodeRequest;
import com.amit.google_maps_java.dto.ReverseGeocodeResponse;
import com.amit.google_maps_java.service.GeocodingService;
import com.amit.google_maps_java.service.ReverseGeocodingService;
import com.google.maps.errors.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GeocodingController {

    private final GeocodingService geocodingService;
    private final ReverseGeocodingService reverseGeocodingService;


    @PostMapping("/reverse-geocode")
    public ResponseEntity<ReverseGeocodeResponse> reverse(@RequestBody ReverseGeocodeRequest req) throws Exception {
        ReverseGeocodeResponse reverse = reverseGeocodingService.reverse(req.getLatitude(), req.getLongitude());
        return ResponseEntity.ok(reverse);
    }

    @PostMapping("/geocode")
    public ResponseEntity<GeoCodeResponse> geocode(@RequestBody GeoCodeRequest request) throws IOException, InterruptedException, ApiException {
        GeoCodeResponse geocode = geocodingService.invokeGoogle(request.getAddress());
        return ResponseEntity.ok(geocode);
    }
}
