package com.company.controller;

import com.company.model.Planet;
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

    public void createPlanet(Context context) {
        // Henter parameterene fra URL'en og setter de som variabler
        String systemName = context.pathParam("planet-system-id");

        // Lager en ny planet
        Planet newPlanet = new Planet();

        // Henter ut verdiene fra form (som kommer fra context) og setter veriene til en ny planet
        newPlanet.setName(context.formParam("name"));
        newPlanet.setRadius(Double.parseDouble(context.formParam("radius")));
        newPlanet.setMass(Double.parseDouble(context.formParam("mass")));
        newPlanet.setPictureUrl(context.formParam("pictureUrl"));
        newPlanet.setSemiMajorAxis(Double.parseDouble(context.formParam("semiMajorAxis")));
        newPlanet.setEccentricity(Double.parseDouble(context.formParam("eccentricity")));
        newPlanet.setOrbitalPeriod(Double.parseDouble(context.formParam("orbitalPeriod")));

        // Henter solen i systemet vi er i og setter dette som sjternen til den nye planeten
        newPlanet.setCentralCelestialBody(repository.getPlanetSystem(systemName).getCenterStar());

        // Lager den nye planeten
        repository.createPlanet(systemName, newPlanet);

        context.redirect("/planet-system/" + systemName);
    }

    public void updatePlanet(Context context) {
        // Henter parameterene fra URL'en og setter de som variabler
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        // Lager en ny planet
        Planet newPlanet = new Planet();

        // Henter ut verdiene fra form (som kommer fra context) og setter veriene til en ny planet
        newPlanet.setName(context.formParam("name"));
        newPlanet.setRadius(Double.parseDouble(context.formParam("radius")));
        newPlanet.setMass(Double.parseDouble(context.formParam("mass")));
        newPlanet.setPictureUrl(context.formParam("pictureUrl"));
        newPlanet.setSemiMajorAxis(Double.parseDouble(context.formParam("semiMajorAxis")));
        newPlanet.setEccentricity(Double.parseDouble(context.formParam("eccentricity")));
        newPlanet.setOrbitalPeriod(Double.parseDouble(context.formParam("orbitalPeriod")));

        // Henter solen i systemet vi er i og setter dette som sjternen til den nye planeten
        newPlanet.setCentralCelestialBody(repository.getPlanetSystem(systemName).getCenterStar());

        // Updaterer planeten
        repository.updatePlanet(systemName, planetName, newPlanet);

        context.redirect("/planet-system/" + systemName);
    }

    public void deletePlanet(Context context) {
        // Henter parameterene fra URL'en og setter de som variabler
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        // Sletter planeten
        repository.deletePlanet(systemName, planetName);

        context.redirect("/planet-system/" + systemName);
    }

    private void sortPlanets(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String sortType = context.queryParam("sort_by");

        repository.sort(systemName, sortType);
    }
}
