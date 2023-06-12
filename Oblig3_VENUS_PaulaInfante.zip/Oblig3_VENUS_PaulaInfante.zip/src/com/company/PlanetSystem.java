package com.company;
import java.util.ArrayList;

public class PlanetSystem {
    private String name;
    private Star centerStar;
    private ArrayList<Planet> planets;

    public PlanetSystem(String name, Star centerStar, ArrayList<Planet> planets) {
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

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    public Planet smallPlanet() {
        Planet smallestPlanet = planets.get(0);

        for (Planet onePlanet : planets) {

            if (smallestPlanet.getRadius() > onePlanet.getRadius()) {
                smallestPlanet = onePlanet;
            }
        }
        return smallestPlanet;
    }

    public Planet bigPlanet() {
        Planet biggestPlanet = planets.get(0);

        for (Planet onePlanet : planets) {

            if (biggestPlanet.getRadius() < onePlanet.getRadius()) {
                biggestPlanet = onePlanet;
            }
        }
        return biggestPlanet;
    }

    public Planet getPlanetByName(String name) {
         for (Planet onePlanet : planets) {

             if (onePlanet.getName() == name) {

                 return onePlanet;
             }
         }
         return null;
    }

    @Override
    public String toString() {
        return "PlanetSystem = " + name + ", centerStar=" + centerStar + ", planets=" + planets;
    }
}
