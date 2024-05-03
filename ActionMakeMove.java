package org.example.Actions;

import org.example.AnimalType;
import org.example.Coordinates;
import org.example.Entity;
import org.example.StaticObjects.Grass;
import org.example.Map;

import java.util.HashMap;
import java.util.Random;

public class ActionAddGrass extends Action{
    Random random;
    Map mapMap = new Map();
    private final int BOUND = 15;
    public HashMap<Coordinates, Entity> actionAddGrass(HashMap<Coordinates, Entity> map, int grassSummary ) {
        for(int i = 0; i < grassSummary; i++) {
        mapMap.setEntityOnMap(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true), map);
        }
        return map;
    }
}
