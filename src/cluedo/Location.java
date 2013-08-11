package cluedo;

/**Represents a location on the board.
 *
 */
public class Location {
	boolean hasCard;
	boolean hasPlayer;	
	private Player p;
	private int x;
	private int y;

	/**Creates a new Location at a specified position.
	 * 
	 * @param x - X coordinate.
	 * @param y - Y coordinate.
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
		hasPlayer = false;
		hasCard = false;
	}
	/**Creates a new Location at a specified position, 
	 * and a boolean for whether it holds an intrigue card.
	 * 
	 * @param x - X coordinate.
	 * @param y - Y coordinate.
	 * @param notEmpty - Holds intrigue card.
	 */
	public Location(int x, int y, boolean notEmpty) {
		this.x = x;
		this.y = y;
		hasCard = notEmpty;
	}

	/**Returns the X coordinate.
	 * 
	 * @return - X coordinate.
	 */
	public int getX() {
		return x;
	}

	/**Returns the Y coordinate.
	 * 
	 * @return - Y coordinate.
	 */	
	public int getY() {
		return y;
	}

	/**Returns whether a player is standing on this location.
	 * 
	 * @return - Boolean holds a player
	 */
	public boolean hasPlayer() {
		return hasPlayer;
	}

	/**Returns whether this location has an intrigue card.
	 * 
	 * @return - Boolean holds intrigue card.
	 */
	public boolean hasCard() {
		return hasCard;
	}

	/**Set's whether this location holds a player.
	 * 
	 * @param player - Boolean for holding a Player
	 */
	public void setPlayer(boolean player) {
		hasPlayer = player;
	}

	/**Get the player standing on this location.
	 * 
	 * @return - The player here.
	 */
	public Player getP() {
		return p;
	}

	/**Sets the player standing on this location.
	 * 
	 * @param p - The player to set.
	 */
	public void setP(Player p) {
		this.p = p;
	}
}
