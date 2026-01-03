package com.amit.google_maps_java.config;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.maps.addressvalidation.v1.AddressValidationClient;
import com.google.maps.addressvalidation.v1.AddressValidationSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class MapsConfiguration {

    @Value("${maps.api.key}")
    private String mapsApiKey;

    @Bean
    public AddressValidationClient addressValidationClient() throws IOException {
        if (mapsApiKey == null || mapsApiKey.isBlank()) {
            throw new IllegalStateException("maps api key not set");
        }

        var headers = Map.of("x-goog-api-key", mapsApiKey);

        AddressValidationSettings settings = AddressValidationSettings.newBuilder()
                .setHeaderProvider(FixedHeaderProvider.create(headers))
                .setCredentialsProvider(NoCredentialsProvider.create())
                .build();

        return AddressValidationClient.create(settings);
    }
}
