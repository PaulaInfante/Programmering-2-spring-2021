package com.company.controller;

import com.company.repository.IUniverseRepository;
import io.javalin.http.Context;

public class PlanetController {
    IUniverseRepository repository;

    public PlanetController(IUniverseRepository repository) {
        this.repository = repository;
    }

    public void getPlanetSystems(Context context) {
        context.json(repository.getAllPlanetSystem());
    }

    public void getPlanetSystem(Context context) {
        String systemName = context.pathParam("planet-system-id");

        context.json(repository.getPlanetSystem(systemName));
    }

    public void getPlanets(Context context) {
        String systemName = context.pathParam("planet-system-id");

        sortPlanets(context);

        context.json(repository.getAllPlanetsInSystem(systemName));
    }

    public void getPlanet(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        context.json(repository.getPlanet(systemName, planetName));
    }

    private void sortPlanets(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String sortType = context.queryParam("sort_by");

        repository.sort(systemName, sortType);
    }
}
