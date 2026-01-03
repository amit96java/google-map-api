package com.amit.google_maps_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressValidationResponse {
    private Boolean valid;
    private String formattedAddress;
}
