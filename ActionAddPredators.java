package org.example.Actions;

import org.example.AnimalType;
import org.example.Coordinates;
import org.example.Entity;
import org.example.Map;
import org.example.Predators.*;

import java.util.HashMap;
import java.util.Random;

public class ActionAddPredators extends Action {
    Random random;
    Map mapMap = new Map();
    private final int BOUND = 15;
    public HashMap<Coordinates, Entity> actionAddHerbivores(HashMap<Coordinates, Entity> map, int herbivoreSummary ) {
        for(int i = 0; i < herbivoreSummary; i++) {
            mapMap.setEntityOnMap(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bear(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", 20,  AnimalType.PREDATOR), map);
            mapMap.setEntityOnMap(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", 5, AnimalType.PREDATOR), map);
            mapMap.setEntityOnMap(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tiger(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", 15, AnimalType.PREDATOR), map);

        }
        return map;
    }
}
