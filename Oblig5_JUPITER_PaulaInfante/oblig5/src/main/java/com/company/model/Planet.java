package com.company.model;

import com.fasterxml.jackson.annotation.*;

public class Planet extends NaturalSatellite{
    public static final double JUPITER_RADIUS_IN_KM = 71492;
    public static final double JUPITER_MASS_IN_KG = 1.898E27;
    public static final double EARTH_RADIUS_IN_KM = 6371;
    public static final double EARTH_MASS_IN_KG = 5.972E24;

    public Planet() {
    }

    public Planet(String name, double radius, double mass, String pictureURL, double samiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name, radius, mass, pictureURL, samiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    /*
    Getter og Setter
     */
    @JsonIgnore
    public double getSurfaceGravity() {
        // g = GM / R²
        return (CelestialBody.GRAVITATIONAL_CONSTANT * getMassInKg()) / Math.pow(getRadiusInMeter(), 2);
    }

    @Override
    @JsonIgnore
    public double getRadiusInKm() {
        return getRadius() * JUPITER_RADIUS_IN_KM;
    }

    @JsonIgnore
    private double getRadiusInMeter() {
        return getRadiusInKm() * 1000;
    }

    @Override
    @JsonIgnore
    public double getMassInKg() {
        return getMass() * JUPITER_MASS_IN_KG;
    }

    @JsonIgnore
    public double getMassInMearth() {
        return getMassInKg() / EARTH_MASS_IN_KG;
    }

    @JsonIgnore
    public double getRadiusInRearth() {
        return getRadiusInKm() / EARTH_RADIUS_IN_KM;
    }

    /*
    Override
     */
    @Override
    public String toString() {
        return String.format("%s has a radius of %s Rjup and a mass of %s Mjup", getName(), getRadius(), getMass());
    }
}
