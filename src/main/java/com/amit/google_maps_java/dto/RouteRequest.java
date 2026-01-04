package com.amit.google_maps_java.dto;

import com.amit.google_maps_java.model.GeoPoint;

import java.util.List;

public record RouteRequest(GeoPoint origin, GeoPoint destination,
                           String travelMode, Boolean alternativeRoutes,
                           List<GeoPoint> waypoints) {
}
