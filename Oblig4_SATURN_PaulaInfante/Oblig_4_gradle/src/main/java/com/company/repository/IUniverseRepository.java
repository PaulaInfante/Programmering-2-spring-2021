package com.company.repository;

import com.company.model.Planet;
import com.company.model.PlanetSystem;

import java.util.ArrayList;

public interface IUniverseRepository {
    ArrayList<Planet> getAllPlanetsInSystem(String systemName);

    ArrayList<PlanetSystem> getAllPlanetSystem();
    PlanetSystem getPlanetSystem(String planetSystemName);

    Planet getPlanet(String systemName, String planetName);

    void sort(String systemName, String sortType);

}
