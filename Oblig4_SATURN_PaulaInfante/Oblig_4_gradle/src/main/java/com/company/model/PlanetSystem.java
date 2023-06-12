package com.company.model;

import java.util.ArrayList;
import java.util.Comparator;

public class PlanetSystem {
    private String name, pictureUrl;
    private Star centerStar;
    private ArrayList<Planet> planets = new ArrayList<>();

    public PlanetSystem(String name, Star centerStar, String pictureURL) {
        this.name = name;
        this.centerStar = centerStar;
        this.pictureUrl = pictureURL;
    }

    public Planet getPlanet(String name) {
        for (Planet aPlanet : planets ) {
            if (aPlanet.getName().equalsIgnoreCase(name)) {
                return aPlanet;
            }
        }

        return null;
    }

    public Planet getSmallestPlanet() {
        if (planets.size() == 0)
            return null;

        Planet smallestPlanet = planets.get(0);

        for (Planet currentPlanet : planets) {
            if (currentPlanet.getRadius() < smallestPlanet.getRadius()) {
                smallestPlanet = currentPlanet;
            }
            else if (currentPlanet.getRadius() == smallestPlanet.getRadius() &&
                    currentPlanet.getMass() < smallestPlanet.getMass()) {
                smallestPlanet = currentPlanet;
            }
        }

        return smallestPlanet;
    }

    public Planet getLargestPlanet() {
        if (planets.size() == 0)
            return null;

        Planet largestPlanet = planets.get(0);

        for (Planet currentPlanet : planets) {
            if (currentPlanet.getRadius() > largestPlanet.getRadius()) {
                largestPlanet = currentPlanet;
            }
            else if (currentPlanet.getRadius() == largestPlanet.getRadius() &&
                    currentPlanet.getMass() > largestPlanet.getMass()) {
                largestPlanet = currentPlanet;
            }
        }

        return largestPlanet;
    }

    public void addPlanet(Planet aPlanet) {
        planets.add(aPlanet);
    }

    public ArrayList<Planet> getPlanets() {
        return new ArrayList<>(planets);
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

    // Object.java
    @Override
    public String toString() {
        return name + " has " + planets.size() +
                " planets that revolve around the star " +
                centerStar.getName();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void sortPlanets(String sortType) {
        if (sortType == null) {
            return;
        }

        switch (sortType) {
            case "name":
                planets.sort((planet1, planet2) -> planet1.getName().compareToIgnoreCase(planet2.getName()));
                break;
            case "mass":
                //planets.sort(Comparator.comparingDouble(CelestialBody::getMass));
                planets.sort((planet1, planet2) -> (int)(planet1.getMass() - planet2.getMass()));
                break;
            case "radius":
                //planets.sort(Comparator.comparingDouble(CelestialBody::getRadius));
                planets.sort((planet1, planet2) -> (int)(planet1.getRadius() - planet2.getRadius()));
                break;
            case "num":
                // planets.sort(Comparator.comparingInt(CelestialBody::getIndex));
                planets.sort((planet1, planet2) -> (planet1.getIndex() - planet2.getIndex()));
                break;
        }
    }
}
