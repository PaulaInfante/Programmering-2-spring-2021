package com.company.model;

import org.jetbrains.annotations.NotNull;

public abstract class CelestialBody implements Comparable<CelestialBody> {
    private String name, pictureUrl;
    private double radius, mass;
    private static int count;
    private int index;

    public static final double GRAVITATIONAL_CONSTANT = 6.67408E-11;

    public CelestialBody(String name, double radius, double mass, String pictureURL) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.pictureUrl = pictureURL;
        this.index = count++;
    }

    public abstract double getMassInKg();
    public abstract double getRadiusInKm();

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


    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(@NotNull CelestialBody o) {
        return (int)(this.getMass() - o.getMass());
    }
}
