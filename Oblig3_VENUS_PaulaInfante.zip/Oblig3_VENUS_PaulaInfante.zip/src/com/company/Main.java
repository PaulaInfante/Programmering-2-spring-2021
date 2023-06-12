package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Star sun = new Star("Sun",1.0,1.0,5777);

        ArrayList<Planet> planetsTemp = new ArrayList<Planet>();

        planetsTemp.add(new Planet("Earth", 0.08911486599899289, 0.003146469968387777, 1, 0.017, 365, sun));
        planetsTemp.add(new Planet("Uranus", 0.35475297935433336, 0.04573761854583773, 19.2184, 0.046, 30660, sun));
        planetsTemp.add(new Planet("Mars", 0.04741089912158004, 3.3667017913593256E-4, 1.524, 0.093, 687, sun));
        planetsTemp.add(new Planet("Venus", 0.08465003077267387, 0.002564278187565859, 0.723, 0.007, 225, sun));
        planetsTemp.add(new Planet("Saturn", 0.8145247020645666, 0.2994204425711275, 9.5826, 0.057, 10585, sun));
        planetsTemp.add(new Planet("Neptune", 0.34440217087226543, 0.05395152792413066, 30.11, 0.010, 60225, sun));
        planetsTemp.add(new Planet("Mercury", 0.03412549655905556, 1.7297154899894627E-4, 0.387, 0.206, 88, sun));
        planetsTemp.add(new Planet("Jupiter", 1.0, 1.0, 5.20440, 0.049, 4380, sun));

	    PlanetSystem solarSystem = new PlanetSystem("Solar system", sun, planetsTemp);

	    System.out.println("Earth has a distance of " + solarSystem.getPlanetByName("Earth").distanceToCentralBody(0) + "km to the Sun at 0 degrees");
        System.out.println("Earth has a distance of " + solarSystem.getPlanetByName("Earth").distanceToCentralBody(90) + "km to the Sun at 90 degrees");
        System.out.println("Earth has a distance of " + solarSystem.getPlanetByName("Earth").distanceToCentralBody(180) + "km to the Sun at 180 degrees");
        System.out.println("Earth has a distance of " + solarSystem.getPlanetByName("Earth").distanceToCentralBody(270) + "km to the Sun at 270 degrees");
        System.out.println("Earth has a distance of " + solarSystem.getPlanetByName("Earth").distanceToCentralBody(360) + "km to the Sun at 360 degrees");

        System.out.println( );

        Planet earth = solarSystem.getPlanetByName("Earth");
        System.out.println("At a distance of " + earth.distanceToCentralBody(0) + "km, Earth has a velocity of " + earth.orbitalVelocity(earth.distanceToCentralBody(0)) + "km/s");
        System.out.println("At a distance of " + earth.distanceToCentralBody(45) + "km, Earth has a velocity of " + earth.orbitalVelocity(earth.distanceToCentralBody(45)) + "km/s");
        System.out.println("At a distance of " + earth.distanceToCentralBody(90) + "km, Earth has a velocity of " + earth.orbitalVelocity(earth.distanceToCentralBody(90)) + "km/s");
        System.out.println("At a distance of " + earth.distanceToCentralBody(135) + "km, Earth has a velocity of " + earth.orbitalVelocity(earth.distanceToCentralBody(135)) + "km/s");
        System.out.println("At a distance of " + earth.distanceToCentralBody(180) + "km, Earth has a velocity of " + earth.orbitalVelocity(earth.distanceToCentralBody(180)) + "km/s");


    }
}
