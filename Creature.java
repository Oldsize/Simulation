package org.example;

public abstract class Creature extends Entity {
    public final int creatureSpeed;
    public int creatureHealth;
    public Creature(int creatureSpeed, int creatureHealth, Coordinates coordinates, String entityString, AnimalType animalType) {
        super(coordinates, entityString, animalType);
        this.creatureHealth = creatureHealth;
        this.creatureSpeed = creatureSpeed;
    }

    

}
