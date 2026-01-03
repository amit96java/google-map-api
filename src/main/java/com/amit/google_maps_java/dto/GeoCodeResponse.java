package com.amit.google_maps_java.dto;

import com.amit.google_maps_java.model.GeocodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeResponse {
    private String formattedAddress;
    private double latitude;
    private double longitude;
    private String placeId;
    private List<String> types;
    private boolean partialMatch;

    private int resultCount;
    private boolean ambiguous;
    private List<String> candidateResponse;

    private GeocodeStatus geocodeStatus;
}
