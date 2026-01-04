package com.amit.google_maps_java.dto;

import com.amit.google_maps_java.model.GeoPoint;

public record DriverRadiusRequest(GeoPoint pickup, double radiusKm) {
}
