package com.amit.google_maps_java.controller;

import com.amit.google_maps_java.dto.PlanDeliveryRequest;
import com.amit.google_maps_java.dto.PlanDeliveryResponse;
import com.amit.google_maps_java.service.DeliveryPlanningService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DeliveryPlanningController {
    private final DeliveryPlanningService deliveryPlanningService;

    @PostMapping("/delivery/plan")
    public PlanDeliveryResponse planDelivery(@RequestBody PlanDeliveryRequest request) throws Exception {
        return deliveryPlanningService.planDelivery(request);
    }
}
