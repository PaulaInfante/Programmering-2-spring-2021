package com.company;

public class Main {

    public static void main(String[] args) {
        Star sun = new Star("Sun",1.0,1.0,5777);
        Planet earth = new Planet("Earth", 0.08911486599899289, 0.003146469968387777);
        Planet uranus = new Planet("Uranus", 0.35475297935433336, 0.04573761854583773);
        Planet mars = new Planet("Mars", 0.04741089912158004, 3.3667017913593256E-4);
        Planet venus = new Planet("Venus", 0.08465003077267387, 0.002564278187565859);
        Planet saturn = new Planet("Saturn", 0.8145247020645666, 0.2994204425711275);
        Planet neptune = new Planet("Neptune", 0.34440217087226543, 0.05395152792413066);
	    PlanetSystem solarSystem = new PlanetSystem("Solar system", sun, new Planet[]{earth, uranus, mars, venus, saturn, neptune});

	    System.out.println(earth);
        System.out.println(sun);
        System.out.println(solarSystem);


        System.out.println(saturn.radiusInKm());
        System.out.println(saturn.massInKg());

        System.out.println(sun.radiusInKm());
        System.out.println(sun.massInKg());


        System.out.println(neptune.surfaceGravity());


        System.out.println(solarSystem.bigPlanet());
        System.out.println(solarSystem.smallPlanet());

    }
}
