package com.amit.google_maps_java.dto;

import com.amit.google_maps_java.model.Warehouse;

public record NearestWarehouseResponse(Warehouse warehouse, double distanceKm) {
}
