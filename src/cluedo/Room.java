package cluedo;

/**Represents a room in the game.
 * There are 10 different rooms(including the pool)
 *
 */
public class Room extends Card{
	private String name;
	private int roomKey;
	private Location spot = null;

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
			spot = new Location(26, 3);
			break;
		case 1:
			name = "Patio";
			spot = new Location(13, 3);
			break;
		case 2:
			name = "Spa";
			spot = new Location(3, 3);		
			break;
		case 3:
			name = "Dining Room";
			spot = new Location(25, 13);
			break;
		case 9:
			name = "Observatory";
			spot = new Location(4, 24);
			break;
		case 5:
			name = "Theatre";
			spot = new Location(3, 10);
			break;
		case 6:
			name = "Living Room";
			spot = new Location(4, 16);
			break;
		case 7:
			name = "Guest House";
			spot = new Location(26, 26);
			break;
		case 8:
			name = "Hall";
			spot = new Location(15, 24);
			break;
		case 4:
			name = "Pool";
			spot = new Location(15, 15);
			break;		
		case 10:
			name = "Ouside";
			spot = null;
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

	/**Get the name of the room
	 * 
	 * @return - String name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**Get the set room spot.
	 * 
	 * @return - Location
	 */
	public Location getSpot() {
		return spot;
	}

	/**Sets this room's spot.
	 * Spot is the location a player is displayed on the board when inside the room.
	 * 
	 * @param spot - Location
	 */
	public void setSpot(Location spot) {
		this.spot = spot;
	}

}
