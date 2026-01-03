package com.amit.google_maps_java.controller;

import com.amit.google_maps_java.dto.GeoCodeRequest;
import com.amit.google_maps_java.dto.GeoCodeResponse;
import com.amit.google_maps_java.service.GeocodingService;
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

    @PostMapping("/geocode")
    public ResponseEntity<GeoCodeResponse> geocode(@RequestBody GeoCodeRequest request) throws IOException, InterruptedException, ApiException {
        GeoCodeResponse geocode = geocodingService.invokeGoogle(request.getAddress());
        return ResponseEntity.ok(geocode);
    }
}
