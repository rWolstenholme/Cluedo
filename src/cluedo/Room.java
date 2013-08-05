package cluedo;

import java.util.HashSet;
import java.util.Set;

public class Room extends Card{
	private Set<Location> inRoom = new HashSet<Location>();
	private String name;
	private Set<Location> locations;
	private int roomKey;

	public Room(int roomKey) {
		setName(roomKey);
		this.roomKey = roomKey;
	}
	
	private void setName(int roomKey) {
		switch (roomKey) {
		case 0:
			name = "Kitchen";
			break;
		case 1:
			name = "Patio";
			break;
		case 2:
			name = "Spa";
			break;
		case 3:
			name = "Dining Room";
			break;
		case 4:
			name = "Pool";
			break;
		case 5:
			name = "Theatre";
			break;
		case 6:
			name = "Living Room";
			break;
		case 7:
			name = "Guest House";
			break;
		case 8:
			name = "Hall";
			break;
		case 9:
			name = "Observatory";
			break;
		case 10:
			name = "Ouside";
			break;
		}
	}
	
	public int getKey(){
		return roomKey;
	}
	
	public void addLocation(Location l) {
		locations.add(l);
	}

}
