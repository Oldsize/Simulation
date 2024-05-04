package org.example;

import org.example.Herbivores.Bunny;
import org.example.Herbivores.Cock;
import org.example.Herbivores.Herbivore;
import org.example.Herbivores.Sheep;
import org.example.Predators.Bear;
import org.example.Predators.Predator;
import org.example.Predators.Tiger;
import org.example.Predators.Wolf;
import org.example.StaticObjects.Grass;
import org.example.StaticObjects.Rock;
import org.example.StaticObjects.StaticObject;
import org.example.StaticObjects.Tree;

import java.util.*;

public class Map {
    private HashMap<Coordinates, Entity> map = new HashMap<>();
    public HashMap<Coordinates, Entity> getMap() { return map; }

    private final int BOUND = 30;


    public boolean isSquareEmpty(Coordinates coordinates) {
        return !map.containsKey(coordinates);
    }
    
    

    private boolean isSquareAvailableForMove(Coordinates coordinates) {
        if (coordinates.x > BOUND || coordinates.y > BOUND) return false;
        if (coordinates.x <= 0 || coordinates.y <= 0) return false;
        if (isSquareEmpty(coordinates)) return true;
        return true;
    }

    private Set<Coordinates> getAvailableToMoveCoordinates(Coordinates coordinates) {
        Set<Coordinates> AvailableCoordinates = new HashSet<>();
        if(isSquareAvailableForMove(new Coordinates(coordinates.x-1, coordinates.y+1))) { //-1 +1, -1 -1, 0 +1, 0 -1, +1 +1, +1 -1, -1 0, +1 0
            AvailableCoordinates.add(new Coordinates(coordinates.x-1, coordinates.y+1));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x-1, coordinates.y-1))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x-1, coordinates.y-1));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x, coordinates.y+1))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x, coordinates.y+1));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x, coordinates.y-1))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x, coordinates.y-1));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x+1, coordinates.y+1))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x+1, coordinates.y+1));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x+1, coordinates.y-1))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x+1, coordinates.y-1));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x-1, coordinates.y))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x-1, coordinates.y));
        } else if(isSquareAvailableForMove(new Coordinates(coordinates.x+1, coordinates.y))) {
            AvailableCoordinates.add(new Coordinates(coordinates.x+1, coordinates.y));
        }
            return AvailableCoordinates;
    }  
    public Coordinates selectAndReturnRandomAvailableCoordinatesToMove(Coordinates coordinates) {
    	Random random = new Random();
    	Set<Coordinates> coordinatesVariables = getAvailableToMoveCoordinates(coordinates);
        Coordinates[] coordinatesArray = coordinatesVariables.toArray(new Coordinates[0]);
    	int variablesMoves = coordinatesArray.length;
        if (variablesMoves == 0) {
        	return null;
        } else if (variablesMoves == 1) {
        	return coordinatesArray[0];
        } else if (variablesMoves > 1) {
            int maxRandomNumber = variablesMoves * 20;
        	int randomVarious = random.nextInt(maxRandomNumber);
        	if (randomVarious <= 20) {
        		return coordinatesArray[0];
        	} else if (randomVarious >= 20 && randomVarious <= 40) {
        		return coordinatesArray[1];
        	} else if (randomVarious >= 40 && randomVarious <= 60) {
        		return coordinatesArray[2];
        	} else if (randomVarious >= 60 && randomVarious <= 80) {
        		return coordinatesArray[3];
        	} else if (randomVarious >= 80 && randomVarious <= 100) {
        		return coordinatesArray[4];
        	} else if (randomVarious >= 100 && randomVarious <= 120) {
        		return coordinatesArray[5];
        	} else if (randomVarious >= 120 && randomVarious <= 140) {
        		return coordinatesArray[6];
        	} else if (randomVarious >= 140 && randomVarious <= 160) {
        		return coordinatesArray[7];
        	} else {
        		return null;
        	}
        }
        return coordinates;
    }
    public HashMap<Coordinates, Entity> makeMove(Coordinates coord1, Coordinates coord2, HashMap<Coordinates,Entity> map) {
        Entity temp = map.get(coord1);
        if (temp.animalType == AnimalType.HERBIVORE) {
            Herbivore herbivore = (Herbivore) map.get(coord1);
            herbivore.creatureHealth--;
            herbivore.coordinates = coord2;
            map.put(coord2, herbivore);
            map.remove(coord1);
            return map;
        } else if (temp.animalType == AnimalType.PREDATOR) {
            Predator predator = (Predator) map.get(coord1);
            predator.coordinates = coord2;
            predator.creatureHealth--;
            map.put(coord2, predator);
            map.remove(coord1);
            return map;
        }
        
        return map;
    }
    public Coordinates checkingGrassAround(Coordinates coordinatesHerbivore, HashMap<Coordinates, Entity> map) {
        Coordinates coordinates = new Coordinates(-1, -1);

        // -1 +1
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y + 1))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y + 1));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y + 1));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // -1 -1
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y - 1))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y - 1));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y - 1));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // 0 +1
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x, coordinatesHerbivore.y + 1))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x, coordinatesHerbivore.y + 1));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x, coordinatesHerbivore.y + 1));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // 0 -1
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x, coordinatesHerbivore.y - 1))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x, coordinatesHerbivore.y - 1));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x, coordinatesHerbivore.y - 1));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // +1 +1
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y + 1))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y + 1));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y + 1));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // +1 -1
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y - 1))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y - 1));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y - 1));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // -1 0
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x - 1, coordinatesHerbivore.y));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        // +1 0
        if (map.containsKey(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y))) {
            Entity entity = map.get(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y));
            if (entity.animalType != AnimalType.HERBIVORE && entity.animalType != AnimalType.PREDATOR && entity.animalType != AnimalType.STATICOBJECT) {
                StaticObject staticObject = (StaticObject) map.get(new Coordinates(coordinatesHerbivore.x + 1, coordinatesHerbivore.y));
                if (staticObject.eatable) {
                    return staticObject.coordinates;
                }
            }
        }

        return coordinates;
    }


    public Coordinates checkingHerbivoresAround(Coordinates coordinatesPredator, HashMap<Coordinates, Entity> map) {
        Coordinates coordinates = new Coordinates(-1, -1);

        // -1 +1
        if (map.containsKey(new Coordinates(coordinatesPredator.x - 1, coordinatesPredator.y + 1))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x - 1, coordinatesPredator.y + 1));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // -1 -1
        if (map.containsKey(new Coordinates(coordinatesPredator.x - 1, coordinatesPredator.y - 1))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x - 1, coordinatesPredator.y - 1));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // 0 +1
        if (map.containsKey(new Coordinates(coordinatesPredator.x, coordinatesPredator.y + 1))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x, coordinatesPredator.y + 1));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // 0 -1
        if (map.containsKey(new Coordinates(coordinatesPredator.x, coordinatesPredator.y - 1))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x, coordinatesPredator.y - 1));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // +1 +1
        if (map.containsKey(new Coordinates(coordinatesPredator.x + 1, coordinatesPredator.y + 1))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x + 1, coordinatesPredator.y + 1));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // +1 -1
        if (map.containsKey(new Coordinates(coordinatesPredator.x + 1, coordinatesPredator.y - 1))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x + 1, coordinatesPredator.y - 1));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // -1 0
        if (map.containsKey(new Coordinates(coordinatesPredator.x - 1, coordinatesPredator.y))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x - 1, coordinatesPredator.y));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        // +1 0
        if (map.containsKey(new Coordinates(coordinatesPredator.x + 1, coordinatesPredator.y))) {
            Entity entity = map.get(new Coordinates(coordinatesPredator.x + 1, coordinatesPredator.y));
            if (entity != null && entity.animalType != AnimalType.GRASS && entity.animalType != AnimalType.STATICOBJECT) {
                Creature creature = (Creature) entity;
                if (creature.animalType == AnimalType.HERBIVORE) {
                    return creature.coordinates;
                }
            }
        }

        return coordinates;
    }
    public HashMap<Coordinates, Entity> setEntityOnMap(Coordinates coordinates, Entity entity, HashMap<Coordinates, Entity> map) {
        map.put(coordinates, entity);
        entity.setCoordinates(coordinates);
        return map;
    }


    private void setEntityPosition(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
        entity.setCoordinates(coordinates);
    }
    
    public void setMap(HashMap<Coordinates, Entity> map) {
        this.map = map; 
    }

    public int getArrCoordSize() {
        return arrCoord.size();
    }
    private ArrayList<Coordinates> arrCoord = new ArrayList<>();
    public ArrayList<Coordinates> getAllEntityCoordinates(HashMap<Coordinates, Entity> map) {
        final int MAXENTITIESONMAP = 32;

        int x = 1;
        int y = 1;
        int counter = 0;
        for (int i = 0; i <= 232; i++) {
    		if(map.containsKey(new Coordinates(x, y))) {
                    if(map.get(new Coordinates(x,y)).animalType == AnimalType.PREDATOR || map.get(new Coordinates(x,y)).animalType == AnimalType.HERBIVORE) {
                        arrCoord.add(counter, new Coordinates(x, y));
                        counter++;
                    }
                }
    		x++;
    		if(x==15) {
    			x = 1;
    			y++;
                }
    	}
        if(arrCoord.size() == 0) {
            System.out.println("Game end! Map have 0 entities!");
            System.exit(0);
        }

        return arrCoord;
    }

    public void setupRandomEntitiesAndStaticObjectsPositions() {
        Random random = new Random();
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bunny(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC07", AnimalType.HERBIVORE));
        //BUNNIES
        //🌳
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tree( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF33", AnimalType.STATICOBJECT, false));

        //TREES
        //🐑
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Sheep(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC11", AnimalType.HERBIVORE));

        //SHEEPS
        //🐓
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Cock(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), " \uD83D\uDC13", AnimalType.HERBIVORE));

        //COCKS
        //⛰
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Rock( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\u26F0", AnimalType.STATICOBJECT, false));
        //ROCKS
        //🍃
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Grass( new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83C\uDF43", AnimalType.GRASS, true));
        //GRASSES
        //🐺
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Wolf(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3A", 5, AnimalType.PREDATOR));
        //WOLF
        //🐅
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tiger(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC05", 10, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tiger(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC05", 10, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Tiger(1, 30, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC05", 10, AnimalType.PREDATOR));
        //TIGER
        //🐻
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bear(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3B", 20, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bear(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3B", 20, AnimalType.PREDATOR));
        setEntityPosition(new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), new Bear(1, 15, new Coordinates(random.nextInt(BOUND), random.nextInt(BOUND)), "\uD83D\uDC3B", 20, AnimalType.PREDATOR));
        //BEAR
        
    }
    
    public void checkEntitiesHealths() {
        final int NUMBEROFMAPSELLS = 223;
          // ++, ++ afte 15 
    	int x = 1;
    	int y = 1;
    	for (int i = 0; i <= NUMBEROFMAPSELLS; i++) {
            Entity temp = map.get(new Coordinates(x, y));
            if(!isSquareEmpty(new Coordinates(x, y))) {
                if(temp.animalType != AnimalType.GRASS && temp.animalType != AnimalType.STATICOBJECT){
                if(temp.animalType != AnimalType.PREDATOR || temp.animalType != AnimalType.HERBIVORE) {
                    Creature tempCreature = (Creature) map.get(new Coordinates(x, y));
                        if(tempCreature.creatureHealth == 0) {
                            map.remove(new Coordinates(x, y));
                }
                }
            }}
                
    		x++;
    		if(x==15) {
    			x = 1;
    			y++;
    			
                }
    	}
    }
}
