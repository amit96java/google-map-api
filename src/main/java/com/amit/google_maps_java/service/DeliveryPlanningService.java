package com.amit.google_maps_java.service;

import com.amit.google_maps_java.dto.*;
import com.amit.google_maps_java.model.GeoPoint;
import com.amit.google_maps_java.model.Warehouse;
import com.google.maps.errors.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DeliveryPlanningService {

    private final GeocodingService geocodingService;
    private final NearestWarehouseService nearestWarehouseService;
    private final ReverseGeocodingService reverseGeocodingService;

    public PlanDeliveryResponse planDelivery(PlanDeliveryRequest request) throws Exception {
        // 1. Forward Geocode: address -> coordinates
        GeoCodeResponse geocodeResult = geocodingService.geocode(request.customerAddress());
        GeoPoint customerLocation = new GeoPoint(geocodeResult.getLatitude(), geocodeResult.getLongitude());

        // 2. Service Area Validation

        // 3. Nearest Warehouse Selection
        NearestWarehouseResponse nearestWarehouseResult = nearestWarehouseService.findNearestWarehouse(customerLocation);
        Warehouse nearestWarehouse = nearestWarehouseResult.warehouse();
        double distanceKm = nearestWarehouseResult.distanceKm();

        // 4. Reverse Geocode for display / logging
        ReverseGeocodeResponse reverseResult = reverseGeocodingService.reverse(customerLocation.lat(), customerLocation.lng());

        String displayAddress = reverseResult.getFormattedAddress();

        return PlanDeliveryResponse.builder()
                .inputAddress(request.customerAddress())
                .lat(customerLocation.lat())
                .lng(customerLocation.lng())
                .nearestWarehouse(nearestWarehouse)
                .warehouseDistanceKm(distanceKm)
                .displayAddress(displayAddress)
                .build();
    }
}
