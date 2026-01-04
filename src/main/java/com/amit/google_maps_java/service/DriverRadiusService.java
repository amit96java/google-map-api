package com.amit.google_maps_java.service;

import com.amit.google_maps_java.data.MockSpatialData;
import com.amit.google_maps_java.model.Driver;
import com.amit.google_maps_java.model.DriverDistance;
import com.amit.google_maps_java.model.GeoPoint;
import com.amit.google_maps_java.util.DistanceCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DriverRadiusService {

    public List<DriverDistance> findDriversWithinRadius(GeoPoint pickup, double radiusKm) {
        List<DriverDistance> result = new ArrayList<>();

        List<Driver> drivers = MockSpatialData.DRIVERS;

        for (Driver driver: drivers) {
            double distance = DistanceCalculator.distanceInKm(pickup, driver.location());
            if(distance <= radiusKm) {
                result.add(new DriverDistance(driver, distance));
            }
        }

        result.sort(Comparator.comparingDouble(DriverDistance::distanceKm));
        return result;
    }
}
