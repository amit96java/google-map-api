package com.amit.google_maps_java.dto;

import com.amit.google_maps_java.model.Warehouse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDeliveryResponse {
    private String inputAddress;

    private double lat;
    private double lng;

    private Warehouse nearestWarehouse;
    private double warehouseDistanceKm;

    private String displayAddress;
}
