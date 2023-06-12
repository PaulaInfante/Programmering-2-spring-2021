package com.company;

import java.lang.Math;

public abstract class NaturalSatellite extends CelestialBody{
    private double semiMajorAxis;
    private double eccentricity;
    private int orbitalPeriod;
    private CelestialBody centralCelestialBody;

    public NaturalSatellite(String name, double radius, double mass, double semiMajorAxis, double eccentricity, int orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name , radius,  mass);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = centralCelestialBody;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public int getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(int orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;
    }

    public double distanceToCentralBody(int degrees) {

        double a = semiMajorAxis;
        double e = eccentricity;
        double o = degrees;
        double r = a*(1-Math.pow(e,2)) / 1 + e * Math.cos(o);

        return r;
    }
    public double orbitalVelocity(double distance) {

        double G = 6.67408E-11;
        double M = getMass();
        double r = distance;
        double v = Math.sqrt( G * M / r);

        return v;
    }
}
