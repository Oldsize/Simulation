package org.example.StaticObjects;

import org.example.AnimalType;
import org.example.Coordinates;

public class Grass extends StaticObject {
    public Grass(Coordinates coordinates, String entityString, AnimalType animalType, boolean eatable) {
        super(coordinates, entityString, animalType, eatable);
    }
}
