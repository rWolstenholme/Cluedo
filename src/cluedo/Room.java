package cluedo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Room extends Card{
	private Set<Location> inRoom = new HashSet<Location>();
	private String name;

	public Room(int roomKey) {
		setName(roomKey);
		setLocations();
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
		}
	}
	
	private void setLocations() {

	}

}
