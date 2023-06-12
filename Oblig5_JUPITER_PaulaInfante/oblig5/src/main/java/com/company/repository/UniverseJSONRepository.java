package com.company.repository;

import com.company.model.Planet;
import com.company.model.PlanetSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UniverseJSONRepository implements IUniverseRepository {
    String file;
    HashMap<String, PlanetSystem> planetSystems = new HashMap<>();

    public UniverseJSONRepository(String file) {
        // Ser om fil navnet starter med json/ hvis ikke så legger vi det til
        if (!file.startsWith("filer/")) {
            file = "filer/" + file;
        }

        // Ser om filen slutter med .json
        if (!file.endsWith(".json")) {
            file = file + ".json";
        }

        System.out.println(file);

        this.file = file;

        readFile();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public HashMap<String, PlanetSystem> getPlanetSystems() {
        return planetSystems;
    }

    public void setPlanetSystems(HashMap<String, PlanetSystem> planetSystems) {
        this.planetSystems = planetSystems;
    }


    @Override
    public ArrayList<Planet> getAllPlanetsInSystem(String systemName) {
        PlanetSystem system = getPlanetSystem(systemName);

        if (system != null) {
            return system.getPlanets();
        }

        return new ArrayList<>();
    }

    @Override
    public ArrayList<PlanetSystem> getAllPlanetSystem() {
        return new ArrayList<>(planetSystems.values());
    }

    @Override
    public PlanetSystem getPlanetSystem(String planetSystemName) {
        // Henter ut et planet system fra hashmapet
        return planetSystems.get(planetSystemName);
    }

    @Override
    public Planet getPlanet(String systemName, String planetName) {
        PlanetSystem system = getPlanetSystem(systemName);

        if (system != null) {
            return system.getPlanet(planetName);
        }

        return null;
    }

    @Override
    public void sort(String systemName, String sortType) {
        PlanetSystem system = getPlanetSystem(systemName);

        system.sortPlanets(sortType);
    }

    @Override
    public void readFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            PlanetSystem[] systems = objectMapper.readValue(new File(getFile()), PlanetSystem[].class);

            for (PlanetSystem system : systems) {
                planetSystems.put(system.getName(), system);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile() {
        Thread thread = new Thread(() -> {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            try {
                objectMapper.writeValue(new File(getFile()), planetSystems.values());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    @Override
    public void createPlanet(String systemName, Planet newPlanet) {
        // Henter planet systemet den nye planeten skal leggen inn i
        PlanetSystem system = getPlanetSystem(systemName);

        // Legger planeten til
        system.addPlanet(newPlanet);

        // Skriver endringene til fil
        writeFile();
    }

    @Override
    public void deletePlanet(String systemName, String planetName) {
        // Henter ut planet systemet der vi skal slette en planet
        PlanetSystem system = getPlanetSystem(systemName);

        // Sletter planeten i det systemet
        system.deletePlanet(planetName);

        // Skriver endringene til fil
        writeFile();
    }

    @Override
    public void updatePlanet(String systemName, String planetName, Planet updatePlanet) {
        // Henter ut planet systemet der vi skal slette en planet
        PlanetSystem system = getPlanetSystem(systemName);

        // Oppdaterer planeten med den nye planetet
        system.updatePlanet(planetName, updatePlanet);

        // Skriver endringene til fil
        writeFile();
    }
}
