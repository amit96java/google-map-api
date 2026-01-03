package com.amit.google_maps_java.dto;

import lombok.Data;

@Data
public class ReverseGeocodeRequest {
    private double latitude;
    private double longitude;
}
