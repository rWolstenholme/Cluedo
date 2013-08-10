package cluedo;

/**Represents a weapon in the game.
 * There are nine different weapons.
 *
 */
public class Weapon extends Card{
	private String name;
	
	/**Create a weapon given the nameKey.
	 * 
	 * @param nameKey - A key that refers to a specific weapon (0-8).
	 */
	public Weapon(Integer nameKey){
		switch(nameKey){
		case 0:
			name = "Rope"; break;
		case 1:
			name = "Candlestick"; break;
		case 2:
			name = "Knife"; break;
		case 3:
			name = "Pistol"; break;
		case 4:
			name = "Baseball bat"; break;
		case 5:
			name = "Dumbbell"; break;
		case 6:
			name = "Trophy"; break;
		case 7:
			name = "Poison"; break;
		case 8:
			name = "Axe"; break;
		}
	}

	/**Returns the weapon name.
	 * 
	 * @return String name.
	 */
	public String getName() {
		return name;
	}
}
