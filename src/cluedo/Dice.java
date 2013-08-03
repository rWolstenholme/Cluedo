package cluedo;

import java.util.Random;

public class Dice {
	private int one;
	private int two;
	private Random rand;
	
	public Dice(){
		rand = new Random();
		one = rand.nextInt(6);
		two = rand.nextInt(6);
		//two = rand.nextInt(7);	//If roll == 0. Pickup card.
	}
	
	public void roll(){
		one = rand.nextInt(6);
		two = rand.nextInt(6);
		//two = rand.nextInt(7);
	}
	
	public int getCount() {
		return one + two;	//Return value of both dice rolled.
	}
	
	public boolean isPickup(){
		return two == 0;	//This even needed? 
		//Alex mentioned the second dice having a card pickup side,
		//but I can't find it in any rules I saw.
	}
}
