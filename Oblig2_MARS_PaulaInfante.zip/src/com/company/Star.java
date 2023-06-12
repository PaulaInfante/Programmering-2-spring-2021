package com.company;

public class Star {
    private String name;
    private double radius;
    private double mass;
    private int effectiveTemp;

    public Star(String name, double radius, double mass, int effectiveTemp) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.effectiveTemp = effectiveTemp;
    }

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

    public int getEffectiveTemp() {
        return effectiveTemp;
    }

    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }

    public double massInKg() {
        return mass / 1.98892E30;
    }

    public double radiusInKm() {
        return radius / 695700;
    }

    @Override
    public String toString() {
        return "Star name=" + name + ", radius=" + radius + ", mass=" + mass + ", effectiveTemp=" + effectiveTemp;
    }
}
