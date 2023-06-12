package com.company.model;

import com.fasterxml.jackson.annotation.*;

public class Star extends CelestialBody{
    private double effectiveTemp;

    public static final double SOLAR_RADIUS_IN_KM = 695700;
    public static final double SOLAR_MASS_IN_KG = 1.98892E30;

    public Star() {
    }

    public Star(String name, double radius, double mass, String pictureURL, double effectiveTemp) {
        super(name, radius, mass, pictureURL);
        this.effectiveTemp = effectiveTemp;
    }

    /*
    Getter og Setter
     */
    @Override
    @JsonIgnore
    public double getRadiusInKm() {
        return getRadius() * SOLAR_RADIUS_IN_KM;
    }

    @Override
    @JsonIgnore
    public double getMassInKg() {
        return getMass() * SOLAR_MASS_IN_KG;
    }

    public double getEffectiveTemp() {
        return effectiveTemp;
    }

    public void setEffectiveTemp(double effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }

    /*
    Override
     */
    @Override
    public String toString() {
        return String.format("%s has a radius of %s Rsun, a mass of %s Msun and a effective temperature of %.0f K", getName(), getRadius(), getMass(), effectiveTemp);
    }
}
