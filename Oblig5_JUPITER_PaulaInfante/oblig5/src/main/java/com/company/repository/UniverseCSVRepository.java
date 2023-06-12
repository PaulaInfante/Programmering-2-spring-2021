package com.company.repository;

import com.company.model.Planet;
import com.company.model.PlanetSystem;
import com.company.model.Star;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UniverseCSVRepository implements IUniverseRepository {
    String file;
    HashMap<String, PlanetSystem> planetSystems = new HashMap<>();

    public UniverseCSVRepository(String file) {
        // Ser om fil navnet starter med json/ hvis ikke så legger vi det til
        if (!file.startsWith("filer/")) {
            file = "filer/" + file;
        }

        // Ser om filen slutter med .json
        if (!file.endsWith(".csv")) {
            file = file + ".csv";
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
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;

            while ((row = reader.readLine()) != null) {
                String[] data = row.split(";");

                Star star;
                Planet planet;
                PlanetSystem planetSystem;

                // Tests if the planetSystem exists
                if (planetSystems.containsKey(data[0])) {
                    planetSystem = getPlanetSystem(data[0]);
                } else {
                    planetSystem = new PlanetSystem();

                    planetSystem.setName(data[0]);
                    planetSystem.setPictureUrl(data[1]);
                }

                if (planetSystem.getCenterStar() != null) {
                    star = planetSystem.getCenterStar();
                } else {
                    star = new Star();

                    star.setName(data[2]);
                    star.setRadius(Double.parseDouble(data[3]));
                    star.setMass(Double.parseDouble(data[4]));
                    star.setEffectiveTemp(Double.parseDouble(data[5]));
                    star.setPictureUrl(data[6]);

                    planetSystem.setCenterStar(star);
                }

                if (planetSystem.getPlanet(data[7]) != null) {
                    planet = planetSystem.getPlanet(data[7]);
                } else {
                    planet = new Planet();

                    planet.setName(data[7]);
                    planet.setRadius(Double.parseDouble(data[8]));
                    planet.setMass(Double.parseDouble(data[9]));
                    planet.setSemiMajorAxis(Double.parseDouble(data[10]));
                    planet.setEccentricity(Double.parseDouble(data[11]));
                    planet.setOrbitalPeriod(Double.parseDouble(data[12]));
                    planet.setPictureUrl(data[13]);
                    planet.setCentralCelestialBody(star);
                }

                planetSystem.addPlanet(planet);

                planetSystems.put(planetSystem.getName(), planetSystem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile() {
        Thread thread = new Thread(() -> {
            try (FileWriter writer = new FileWriter(file)) {
                for (PlanetSystem system : planetSystems.values()) {
                    Star star = system.getCenterStar();

                    for (Planet planet : system.getPlanets()) {
                        writer.write(
                                system.getName() + ";"
                                        + system.getPictureUrl() + ";"
                                        + star.getName() + ";"
                                        + star.getRadius() + ";"
                                        + star.getMass() + ";"
                                        + star.getEffectiveTemp() + ";"
                                        + star.getPictureUrl() + ";"
                                        + planet.getName() + ";"
                                        + planet.getRadius() + ";"
                                        + planet.getMass() + ";"
                                        + planet.getSemiMajorAxis() + ";"
                                        + planet.getEccentricity() + ";"
                                        + planet.getOrbitalPeriod() + ";"
                                        + planet.getPictureUrl() + "\n"
                        );
                    }
                }
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
