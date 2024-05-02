package org.example.Herbivores;

import org.example.AnimalType;
import org.example.Coordinates;
import org.example.Creature;
import org.example.Entity;
import java.util.HashMap;


public class Herbivore extends Creature {

    public Herbivore(int creatureSpeed, int creatureHealth, Coordinates coordinates, String entityString, AnimalType animalType) {
        super(creatureSpeed, creatureHealth, coordinates, entityString, animalType);
    }

    
    
    public HashMap<Coordinates, Entity> eatGrass(Coordinates coordinatesHerbivore ,Coordinates coordinatesGrass , HashMap<Coordinates, Entity> map) {
        if(coordinatesGrass.equals(new Coordinates(-1, -1))) {
            return map;
        }
        Herbivore herbivore = (Herbivore) map.get(coordinatesHerbivore);
        herbivore.creatureHealth += 10;
        map.put(coordinatesHerbivore, herbivore);
        map.remove(coordinatesGrass);
        return map;
        
    } 
    
    


}
