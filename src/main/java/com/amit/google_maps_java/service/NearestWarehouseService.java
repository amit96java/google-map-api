package com.amit.google_maps_java.service;

import com.amit.google_maps_java.data.MockSpatialData;
import com.amit.google_maps_java.dto.NearestWarehouseResponse;
import com.amit.google_maps_java.model.GeoPoint;
import com.amit.google_maps_java.model.Warehouse;
import com.amit.google_maps_java.util.DistanceCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearestWarehouseService {

    public NearestWarehouseResponse findNearestWarehouse(GeoPoint customerLocation) {
        List<Warehouse> warehouses = MockSpatialData.WAREHOUSES;

        Warehouse nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Warehouse warehouse: warehouses) {
            double distance = DistanceCalculator.distanceInKm(customerLocation, warehouse.location());

            if (distance < minDistance) {
                minDistance = distance;
                nearest = warehouse;
            }
        }

        return new NearestWarehouseResponse(nearest, minDistance);
    }
}
