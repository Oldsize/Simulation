
package org.example.StaticObjects;

import org.example.AnimalType;
import org.example.Coordinates;
import org.example.Entity;


public class StaticObject extends Entity {
    
    public final boolean eatable; 
    public StaticObject(Coordinates coordinates, String entityString, AnimalType animalType, boolean eatable) {
        super(coordinates, entityString, animalType);
        this.eatable = eatable;
    }
    
    
}
