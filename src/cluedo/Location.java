package cluedo;

/**Represents a location on the board.
 *
 */
public class Location {
	boolean hasCard;
	boolean hasPlayer;	//Could change this to what player??
	private Player p;
	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
		hasPlayer = false;
		hasCard = false;
	}
	
	public Location(int x, int y, boolean notEmpty) {
		this.x = x;
		this.y = y;
		hasCard = notEmpty;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean hasPlayer() {
		return hasPlayer;
	}
	
	public boolean hasCard() {
		return hasCard;
	}
	
	public void setPlayer(boolean player) {
		hasPlayer = player;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
	
	//Method for getRoom it belongs to??
}
