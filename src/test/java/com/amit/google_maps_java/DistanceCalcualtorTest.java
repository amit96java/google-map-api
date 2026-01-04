package com.amit.google_maps_java;

import com.amit.google_maps_java.model.GeoPoint;
import com.amit.google_maps_java.util.DistanceCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DistanceCalcualtorTest {

    @Test
    void checkDistance() {
        double d = DistanceCalculator.distanceInKm(
                new GeoPoint(12.9716, 77.5946),   // Bangalore
                new GeoPoint(12.2958, 76.6394)    // Mysore
        );

        System.out.println("Distance: " + d + " km");
    }
}
