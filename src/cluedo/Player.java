package cluedo;

import java.util.HashSet;

/**Represents a player card in the game.
 * There are six possible different players.
 * However they don't all have to be controlled in the game.
 *
 */
public class Player extends Card{
	private String name;
	private Location atLoc = null; 
	private HashSet<Card> hand;
	private Room roomIn = null;
	private boolean hasLost = false;
	private Location entrance = null;

	/**Create a new player card.
	 * 
	 * @param nameKey - A key that refers to which player they are.
	 */
	public Player(Integer nameKey){
		hand = new HashSet<Card>();
		switch(nameKey){
		case 0:
			name = "Kasandra Scarlet"; break;
		case 1:
			name = "Jack Mustard"; break;
		case 2:
			name = "Diane White"; break;
		case 3:
			name = "Jacob Green"; break;
		case 4:
			name = "Eleanor Peacock"; break;
		case 5:
			name = "Victor Plum"; break;
		}
	}

	/**Returns this player's name.
	 * 
	 * @return - Players name
	 */
	public String getName() {
		return name;
	}

	/**Returns current location.
	 * 
	 * @return - current Location.
	 */
	public Location getAtLoc() {
		return atLoc;
	}

	/**
	 * Returns false if the player has not yet lost the game
	 * @return the players status
	 */
	public boolean getHasLost() {
		return hasLost;
	}

	/**
	 * Changes player state to lost after a failed accusation
	 */
	public void hasLost() {
		this.hasLost = true;
	}

	/**Set's the players current location.
	 * 
	 * @param atLoc - The location at which this player now stands.
	 */
	public void setAtLoc(Location atLoc) {
		this.atLoc = atLoc;
	}

	/**Returns a set representing this players hand of cards.
	 * 
	 * @return - This hand of cards.
	 */
	public HashSet<Card> getHand() {
		return hand;
	}

	/**Adds a given card to this players hand.
	 * 
	 * @param c - Card to add.
	 */
	public void addToHand(Card c) {
		hand.add(c);
	}

	/**Get the room the player is currently inside of.
	 * 
	 * @return - Room
	 */
	public Room getRoomIn() {
		return roomIn;
	}

	/**Set the room the player is currently inside of.
	 * 
	 * @param roomIn - Room
	 */
	public void setRoomIn(Room roomIn) {
		this.roomIn = roomIn;
	}

	/**Returns a string of all the cards in the players hand.
	 * 
	 * @return - String, cards in player's hand.
	 */
	public String handToString() {
		StringBuilder str = new StringBuilder();
		str.append("Holding:");
		for (Card c: hand) {			
			str.append(" " + c.getName() + ",");
		}
		return str.toString().substring(0, str.toString().length()-1);
	}

	/**Returns the players current entrance.
	 * 
	 * @return - Location.
	 */
	public Location getEntrance() {
		return entrance;
	}

	/**Set the entrance of the player.
	 * Entrance is the location of which they entered the current room.
	 * 
	 * @param entrance - Location
	 */
	public void setEntrance(Location entrance) {
		this.entrance = entrance;
	}
}
