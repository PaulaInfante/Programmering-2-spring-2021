package com.company;
import java.util.Arrays;

public class PlanetSystem {
    private String name;
    private Star centerStar;
    private Planet[] planets;

    public PlanetSystem(String name, Star centerStar, Planet[] planets) {
        this.name = name;
        this.centerStar = centerStar;
        this.planets = planets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    public Planet[] getPlanets() {
        return planets;
    }

    public void setPlanets(Planet[] planets) {
        this.planets = planets;
    }

    public Planet smallPlanet() {
        Planet smallestPlanet = planets[0];

        for (Planet onePlanet : planets) {

            if (smallestPlanet.getRadius() > onePlanet.getRadius()) {
                smallestPlanet = onePlanet;
            }
        }
        return smallestPlanet;
    }

    public Planet bigPlanet() {
        Planet biggestPlanet = planets[0];

        for (Planet onePlanet : planets) {

            if (biggestPlanet.getRadius() < onePlanet.getRadius()) {
                biggestPlanet = onePlanet;
            }
        }
        return biggestPlanet;
    }



    @Override
    public String toString() {
        return "PlanetSystem = " + name + ", centerStar=" + centerStar + ", planets=" + Arrays.toString(planets);
    }
}
