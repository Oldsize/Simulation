package org.example.Actions;

import org.example.AnimalType;
import org.example.Coordinates;
import org.example.Entity;
import org.example.Herbivores.Herbivore;
import org.example.Map;
import org.example.Predators.Predator;

import java.util.HashMap;

public class ActionMakeMove extends Action {
    Map map = new Map();
    public HashMap<Coordinates, Entity> makeAllCreatureMove (HashMap<Coordinates, Entity> map)  {
        HashMap<Coordinates, Entity> tempMap = map;
        int x = 0;
        int y = 0;
        for(int i = 0; i < 900; i++) {
            Entity tempEntity = (Entity) map.get(new Coordinates(x, y));
            if(!tempEntity.equals(null) ) {
                if(tempEntity.getAnimalType() == AnimalType.HERBIVORE) {
                    Herbivore herbivore = (Herbivore) map.get(new Coordinates(x, y));
                    tempMap = herbivore.eatGrass(herbivore.coordinates, this.map.checkingGrassAround(herbivore.coordinates, tempMap), tempMap);
                    tempMap = this.map.makeMove(herbivore.coordinates, this.map.selectAndReturnRandomAvailableCoordinatesToMove(herbivore.coordinates), tempMap);


                } else if (tempEntity.getAnimalType() == AnimalType.PREDATOR) {
                    Predator predator = (Predator) map.get(new Coordinates(x, y));
                    tempMap = predator.killHerbivore(predator.coordinates, this.map.checkingGrassAround(predator.coordinates, tempMap), tempMap);
                    tempMap = this.map.makeMove(predator.coordinates, this.map.selectAndReturnRandomAvailableCoordinatesToMove(predator.coordinates), tempMap);



                }


            }


        }

        return tempMap;
    }
}
