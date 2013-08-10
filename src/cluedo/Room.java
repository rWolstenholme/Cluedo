package cluedo;

import java.util.HashSet;
import java.util.Set;

/**Represents a room in the game.
 * There are 10 different rooms(including the pool)
 *
 */
public class Room extends Card{
	private String name;
	private int roomKey;

	/**Create a room using the given roomKey.
	 * 
	 * @param roomKey - A key that refers to a specific room (0-9).
	 */
	public Room(int roomKey) {
		setName(roomKey);
		this.roomKey = roomKey;
	}
	
	/**Use the roomkey to find the correct name;
	 * 
	 * @param roomKey - A key that refers to a specific room (0-9).
	 */
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
			name = "Observatory";
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
			name = "Pool";
			break;		
		case 10:
			name = "Ouside";
			break;
		}
	}
	
	/**Returns the roomKey of this room.
	 * 
	 * @return - int roomKey
	 */
	public int getKey(){
		return roomKey;
	}

	@Override
	public String getName() {
		return name;
	}

}
