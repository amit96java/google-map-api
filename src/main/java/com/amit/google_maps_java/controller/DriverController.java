package com.amit.google_maps_java.controller;

import com.amit.google_maps_java.dto.DriverRadiusRequest;
import com.amit.google_maps_java.model.Driver;
import com.amit.google_maps_java.model.DriverDistance;
import com.amit.google_maps_java.service.DriverRadiusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DriverController {
    private final DriverRadiusService driverRadiusService;

    @PostMapping("/drivers/within-radius")
    public List<DriverDistance> findDrivers(@RequestBody DriverRadiusRequest req) {
        return driverRadiusService.findDriversWithinRadius(req.pickup(), req.radiusKm());
    }
}
