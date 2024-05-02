package org.example.Predators;


import org.example.AnimalType;
import org.example.Coordinates;
import org.example.Creature;
import org.example.Entity;
import org.example.Herbivores.Herbivore;

import java.util.HashMap;

public class Predator extends Creature {
    public final int predatorStrength;
    public Predator(int creatureSpeed, int creatureHealth, Coordinates coordinates, String entityString, int predatorStrength, AnimalType animalType) {
        super(creatureSpeed, creatureHealth, coordinates, entityString, animalType);
        this.predatorStrength = predatorStrength;
    }

    public HashMap<Coordinates, Entity> killHerbivore(Coordinates coordinatesPredator , Coordinates coordinatesHerbivore , HashMap<Coordinates, Entity> map) {
        if(coordinatesHerbivore.equals(new Coordinates(-1, -1))) {
            return map;
        }

        Predator tempPredator = (Predator) map.get(coordinatesPredator);
        Herbivore tempHerbivore = (Herbivore) map.get(coordinatesHerbivore);
        tempHerbivore.creatureHealth = tempHerbivore.creatureHealth - tempPredator.predatorStrength;
        tempPredator.creatureHealth += 10;
        map.put(coordinatesHerbivore, tempHerbivore);
        map.put(coordinatesPredator, tempPredator);

        return map;
    }


}
