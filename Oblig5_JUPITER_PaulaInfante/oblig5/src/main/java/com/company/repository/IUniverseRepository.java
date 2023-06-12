package com.company.repository;

import com.company.model.Planet;
import com.company.model.PlanetSystem;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUniverseRepository {
    String file = null;
    HashMap<String, PlanetSystem> planetSystems = new HashMap<>();

    ArrayList<Planet> getAllPlanetsInSystem(String systemName);

    ArrayList<PlanetSystem> getAllPlanetSystem();
    PlanetSystem getPlanetSystem(String planetSystemName);

    Planet getPlanet(String systemName, String planetName);

    void sort(String systemName, String sortType);

    void readFile();
    void writeFile();

    void createPlanet(String systemName, Planet newPlanet);
    void deletePlanet(String systemName, String planetName);
    void updatePlanet(String systemName, String planetName, Planet updatePlanet);
}
