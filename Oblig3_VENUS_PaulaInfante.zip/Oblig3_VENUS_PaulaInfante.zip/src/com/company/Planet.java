package com.company;
import java.lang.Math;

public class Planet extends NaturalSatellite {
    static final double RJUP = 71492;
    static final double MJUP = 1.898E27;

    Planet(String name, double radius, double mass, double semiMajorAxis, double eccentricity, int orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    public double radiusInKm(){
        return getRadius() / RJUP;
    }

    public double massInKg(){
        return getMass() / MJUP;
    }


    public double surfaceGravity() {
        return 6.67408E-11 * massInKg() / (Math.pow(radiusInKm() / 1000, 2));
    }

}

