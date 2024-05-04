package org.example;

import org.example.Herbivores.Herbivore;
import org.example.Predators.Predator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Simulation  {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.startSimulation();


    }
    
    public void startSimulation() throws InterruptedException {
        Map map = new Map();
        map.setupRandomEntitiesAndStaticObjectsPositions();
        Random random = new Random();

        do {
            map.checkEntitiesHealths();
            HashMap<Coordinates, Entity> tempMap = map.getMap();
            ArrayList arrCoord = map.getAllEntityCoordinates(tempMap);
            int randomNumber = random.nextInt(map.getArrCoordSize());
            Entity tempEntity = tempMap.get(arrCoord.get(randomNumber));
            MapRenderer mapRenderer = new MapRenderer();
            mapRenderer.renderMap(tempMap);
	    Thread.sleep(1800);

            if(tempEntity != null) {
                if(tempEntity.animalType == AnimalType.HERBIVORE) {
                    Herbivore herbivore = (Herbivore) tempMap.get(arrCoord.get(randomNumber));
                    map.setMap(herbivore.eatGrass(herbivore.coordinates, map.checkingGrassAround(herbivore.coordinates, tempMap), tempMap));
                    tempMap = map.getMap();
                    map.setMap(map.makeMove(herbivore.coordinates, map.selectAndReturnRandomAvailableCoordinatesToMove(herbivore.coordinates), tempMap));
            }
            }
            if(tempEntity != null) {
                if(tempEntity.animalType == AnimalType.PREDATOR) {
                    Predator predator = (Predator) tempMap.get(arrCoord.get(randomNumber));
                    map.setMap(predator.killHerbivore(predator.coordinates, map.checkingHerbivoresAround(predator.coordinates, tempMap), tempMap));
                    tempMap = map.getMap();
                    map.setMap(map.makeMove(predator.coordinates, map.selectAndReturnRandomAvailableCoordinatesToMove(predator.coordinates), tempMap));
            }
            }
        } while (true);
    }
	
}
