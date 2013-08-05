package cluedo;

import java.util.HashSet;
import java.util.Set;

public class Board {

	Location grid[][] = new Location [25][29];	//Represents the board??
	
	public Board(Cluedo game) {
		Set<Location> locations = new HashSet<Location>();
		for(int x = 0;x<6;x++){
			for(int y = 0;y<7;y++){
				locations.add(new Location(x,y));
			}
		}
		game.rooms.add(new Room(2,locations));
		locations.clear();
	}
}
