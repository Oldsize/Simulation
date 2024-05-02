package org.example;

import java.util.Objects;

public abstract class Entity {
    AnimalType animalType;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.animalType);
        hash = 17 * hash + Objects.hashCode(this.coordinates);
        hash = 17 * hash + Objects.hashCode(this.entityString);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        
        if (!Objects.equals(this.entityString, other.entityString)) {
            return false;
        }
        if (this.animalType != other.animalType) {
            return false;
        }
        return Objects.equals(this.coordinates, other.coordinates);
    }
    
	

	
    public Coordinates coordinates;
    public final String entityString;
    public Entity(Coordinates coordinates, String entityString, AnimalType animalType) {
        this.animalType = animalType; 
    	this.entityString = entityString;
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    public String getEntityString() {
    	return entityString;
    }
}

