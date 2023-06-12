package com.company.model;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.*;

public class PlanetSystem {
    private String name, pictureUrl;
    private Star centerStar;
    private ArrayList<Planet> planets = new ArrayList<>();

    public PlanetSystem() {
    }

    public PlanetSystem(String name, Star centerStar, String pictureURL) {
        this.name = name;
        this.centerStar = centerStar;
        this.pictureUrl = pictureURL;
    }

    /*
    Getter og Setter
     */
    public Planet getPlanet(String name) {
        for (Planet aPlanet : planets) {
            if (aPlanet.getName().equalsIgnoreCase(name)) {
                return aPlanet;
            }
        }

        return null;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /*
    Andre fungsjoner
     */
    public void addPlanet(Planet aPlanet) {
        planets.add(aPlanet);
    }

    public void deletePlanet(String planetName) {
        // Sletter en planet fra listen hvis planeten sitt navn er lik planetName parameteret, bruker en lambda fungsjon i removeIf metoden
        planets.removeIf(planet -> planet.getName().equals(planetName));
    }

    public void updatePlanet(String originalPlanetName, Planet updatePlanet) {
        // Henter planeten vi vil endre
        Planet planet = getPlanet(originalPlanetName);

        // Setter alle verdiene til updatePlanet objektet
        planet.setName(updatePlanet.getName());
        planet.setRadius(updatePlanet.getRadius());
        planet.setMass(updatePlanet.getMass());
        planet.setPictureUrl(updatePlanet.getPictureUrl());
        planet.setSemiMajorAxis(updatePlanet.getSemiMajorAxis());
        planet.setOrbitalPeriod(updatePlanet.getOrbitalPeriod());
        planet.setCentralCelestialBody(updatePlanet.getCentralCelestialBody());

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

    @JsonIgnore
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

    @JsonIgnore
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

    /*
    Override
     */
    @Override
    public String toString() {
        return name + " has " + planets.size() +
                " planets that revolve around the star " +
                centerStar.getName();
    }
}
