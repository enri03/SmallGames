package com.habitablePlanets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HabitablePlanetApp {

    private static final String SOLID = "solid";
    private static final int AVERAGE_EARTH_TEMPERATURE = 15;
    private static final double EARTH_SUN_DISTANCE_IN_LIGHT_YEARS = 0.00001581;

    public static void main(String[] args) {
        ArrayList<Planet> allPlanets = PlanetRepository.getPlanets();
        System.out.println("The number of plantes is :" + allPlanets.stream().distinct().count());
        Optional<Planet> theBiggest = allPlanets.stream().max(Comparator.comparing(Planet::getSize));
        System.out.println("The biggest planet is " + theBiggest.get().getName() + " with size " + theBiggest.get().getSize() + "km");
        Optional<Planet> theHottest = allPlanets.stream().max(Comparator.comparing(Planet::getTemperature));
        System.out.println("The hottest planet is " + theHottest.get().getName() + " with a max temperature " + theHottest.get().getTemperature() + " celcuis");
        Optional<Planet> theColdest = allPlanets.stream().min(Comparator.comparing(Planet::getTemperature));
        System.out.println("The hottest planet is " + theColdest.get().getName() + " with a max temperature " + theColdest.get().getTemperature() + " celcuis");
        System.out.println("Top 10 most habbitable planets on galaxy :");
        allPlanets.stream().filter(planet -> SOLID.equals(planet.getType())).sorted(Comparator.comparing((Planet p) -> Math.abs(p.getTemperature() - AVERAGE_EARTH_TEMPERATURE))
                .thenComparing(Planet::getDistanceFromEarth)).limit(10).forEach(System.out::println);
        System.out.println("The planet that is at the same distance from its closed star as the earth from the star:");
        allPlanets.stream().sorted(Comparator.comparing(p -> Math.abs(p.getClosestStarDistance() - EARTH_SUN_DISTANCE_IN_LIGHT_YEARS))).limit(1).forEach(System.out::println);
    }
}
