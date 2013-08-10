package cluedo;

import java.util.Random;

/**Represents a two dice being rolled for a player.
 * 
 *
 */
public class Dice {
	private int one;
	private int two;
	private Random rand;
	
	/**Create new dice and rolls it
	 */
	public Dice(){
		rand = new Random();
		one = rand.nextInt(6) + 1;
		two = rand.nextInt(6) + 1;
	}
	
	/**Rolls the dice.
	 * 
	 * @return - The value of the roll (2-12).
	 */
	public int roll(){
		one = rand.nextInt(6) + 1;
		two = rand.nextInt(6) + 1;
		return one + two;
	}
	
	/**Returns the current value of the dice, i.e. the last roll.
	 * 
	 * @return - Current value.
	 */
	public int getCurrent() {
		return one + two;
	}

}
