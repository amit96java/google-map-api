package com.amit.google_maps_java.controller;

import com.amit.google_maps_java.dto.AddressValidationRequest;
import com.amit.google_maps_java.dto.AddressValidationResponse;
import com.amit.google_maps_java.service.AddressValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressValidationController {
    private final AddressValidationService service;

    AddressValidationController(AddressValidationService service) {
        this.service = service;
    }

    @PostMapping("/validate-address")
    public ResponseEntity<AddressValidationResponse> validate(@Valid @RequestBody AddressValidationRequest request) {
        AddressValidationResponse result = service.validate(request.getAddress());
        return ResponseEntity.ok(result);
    }
}
