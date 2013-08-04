package cluedo;

import java.util.Random;

public class Dice {
	private int one;
	private int two;
	private Random rand;
	
	public Dice(){
		rand = new Random();
		one = rand.nextInt(6) + 1;
		two = rand.nextInt(6) + 1;
	}
	
	public int roll(){
		one = rand.nextInt(6) + 1;
		two = rand.nextInt(6) + 1;
		return one + two;
	}
	
	public int getCurrent() {
		return one + two;	//Return value of both dice rolled.
	}

}
