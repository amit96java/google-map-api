package com.amit.google_maps_java.controller;

import com.amit.google_maps_java.dto.NearestWarehouseRequest;
import com.amit.google_maps_java.dto.NearestWarehouseResponse;
import com.amit.google_maps_java.service.NearestWarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WarehouseController {
    private final NearestWarehouseService nearestWarehouseService;

    @PostMapping("/warehouses/nearest")
    public NearestWarehouseResponse findNearestWarehouse(@RequestBody NearestWarehouseRequest request) {
        return nearestWarehouseService.findNearestWarehouse(request.location());
    }
}
