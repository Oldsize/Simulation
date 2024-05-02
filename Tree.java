package org.example.StaticObjects;

import org.example.AnimalType;
import org.example.Coordinates;

public class Tree extends StaticObject {
    public Tree(Coordinates coordinates, String entityString, AnimalType animalType, boolean eatable) {
        super(coordinates, entityString, animalType, eatable);
    }

}
