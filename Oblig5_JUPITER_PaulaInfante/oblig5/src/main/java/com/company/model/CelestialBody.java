package com.company.model;

import com.fasterxml.jackson.annotation.*;
import org.jetbrains.annotations.NotNull;

// For at Jackson skal kunne lese stjerner riktig
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "star")
})

public abstract class CelestialBody implements Comparable<CelestialBody> {
    private String name, pictureUrl;
    private double radius, mass;
    private static int count;
    private int index;

    public static final double GRAVITATIONAL_CONSTANT = 6.67408E-11;

    public CelestialBody() {
    }

    public CelestialBody(String name, double radius, double mass, String pictureURL) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.pictureUrl = pictureURL;
        this.index = count++;
    }

    /*
    Getter og Setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public abstract double getMassInKg();
    public abstract double getRadiusInKm();

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @JsonIgnore
    public int getIndex() {
        return this.index;
    }

    @JsonIgnore
    public void setIndex(int index) {
        this.index = index;
    }

    /*
    Overrride
     */
    @Override
    public int compareTo(@NotNull CelestialBody o) {
        return (int)(this.getMass() - o.getMass());
    }
}
