package com.amit.google_maps_java.service;

import com.amit.google_maps_java.dto.AddressValidationResponse;
import com.google.maps.addressvalidation.v1.AddressValidationClient;
import com.google.maps.addressvalidation.v1.ValidateAddressRequest;
import com.google.maps.addressvalidation.v1.ValidateAddressResponse;
import com.google.maps.addressvalidation.v1.ValidationResult;
import com.google.type.PostalAddress;
import org.springframework.stereotype.Service;

@Service
public class AddressValidationService {
    private final AddressValidationClient client;

    public AddressValidationService(AddressValidationClient client) {
        this.client = client;
    }

    public AddressValidationResponse validate(String rawAddress) {
        PostalAddress postalAddress = PostalAddress.newBuilder().addAddressLines(rawAddress).build();
        ValidateAddressRequest req = ValidateAddressRequest.newBuilder().setAddress(postalAddress).build();

        ValidateAddressResponse resp = client.validateAddress(req);

        ValidationResult result = resp.getResult();
        String formattedAddress = result.getAddress().getFormattedAddress();
        boolean valid = result.getVerdict().getAddressComplete();

        return new AddressValidationResponse(valid, formattedAddress);
    }
}
