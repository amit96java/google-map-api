package com.amit.google_maps_java.dto;

import com.amit.google_maps_java.model.LocationUsability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReverseGeocodeResponse {
    private String formattedAddress;
    private String placeId;
    private String locationType;

    private LocationUsability usability;
}
