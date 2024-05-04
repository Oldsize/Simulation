package org.example;

import java.util.HashMap;

public class MapRenderer {
    int a = 0;		
    private final int NUMBEROFMAPSELLS = 900;
    public void renderMap(HashMap<Coordinates, Entity> map) {
        // ++, ++ afte 15 
    	int x = 1;
    	int y = 1;
    	for (int i = 0; i <= NUMBEROFMAPSELLS; i++) {
    		if(!map.containsKey(new Coordinates(x,y))) {
    			System.out.print("\uD83D\uDFEB");
    			System.out.print("  ");
    		} else {
    			System.out.print(map.get(new Coordinates(x, y)).getEntityString());
    		System.out.print("  ");
    		}
    		x++;
    		if(x ==30) {
    			x = 0;
    			y++;
    			System.out.println("\n");
    		}
			if(y == 30) {
				a++;
				System.out.println(a);
				System.out.println("");
				y = 0;

			}
    	}

    }

}
